package br.com.present.api.gateway.exception.filters;

import br.com.present.api.gateway.exception.keys.GatewayExceptionKeys;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.commons.exception.keys.BaseExceptionKeys;
import br.com.present.commons.util.PresentDataUtils;
import br.com.present.commons.util.PresentInternationalizationUtils;
import br.com.present.commons.util.PresentJsonUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GatewayExceptionFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).onErrorResume(exception -> {
            try {
                Mono<Void> response;
                response = handlerConnectTimeout(exchange, exception);
                response = handlerConnectionRefused(response, exchange, exception);

                return Objects.requireNonNullElseGet(response, () ->
                    switch (((ResponseStatusException) exception).getStatusCode().value()) {
                        case 503 -> handlerServiceUnavailable(exchange, exception);
                        case 504 -> handlerGatewayTimeout(exchange);
                        default -> handlerThrowable(exchange, exception);
                });
            }catch (Exception e){
                return handlerThrowable(exchange, exception);
            }
        }).then();
    }

    private Mono<Void> writeErrorResponse(ServerWebExchange exchange, ApiExceptionResponseDTO apiException) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer dataBuffer = response.bufferFactory().wrap(PresentJsonUtils.toJson(apiException).getBytes());
        response.setStatusCode(apiException.getHttpStatus());

        return response.writeWith(Mono.just(dataBuffer));
    }

    private Mono<Void> handlerConnectTimeout(ServerWebExchange exchange, Throwable exception) {
        if(isConnectTimeout(exception)){
            ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
                    new String[]{PresentInternationalizationUtils.getMessage(GatewayExceptionKeys.GATEWAY_CONNECT_TIMEOUT)},
                    HttpStatus.GATEWAY_TIMEOUT,
                    PresentDataUtils.getZonedDateTimeNow()
            );
            return writeErrorResponse(exchange, apiException);
        }
        return null;
    }

    private boolean isConnectTimeout(Throwable exception) {
        if(isMessageFilled(exception)){
            Pattern pattern = Pattern.compile("connection timed");
            Matcher matcher = pattern.matcher(exception.getMessage());

            return matcher.find();
        }
        return false;
    }

    private Mono<Void> handlerConnectionRefused(Mono<Void> response, ServerWebExchange exchange, Throwable exception) {
        if(response == null && isConnectionRefused(exception)){
            ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
                    new String[]{PresentInternationalizationUtils.getMessage(GatewayExceptionKeys.GATEWAY_CONNECTION_REFUSED)},
                    HttpStatus.SERVICE_UNAVAILABLE,
                    PresentDataUtils.getZonedDateTimeNow()
            );
            return writeErrorResponse(exchange, apiException);
        }
        return response;
    }

    private boolean isConnectionRefused(Throwable exception) {
        if(isMessageFilled(exception)){
            Pattern pattern = Pattern.compile("Connection refused");
            Matcher matcher = pattern.matcher(exception.getMessage());

            return matcher.find();
        }
        return false;
    }

    private boolean isMessageFilled(Throwable exception) {
        return exception.getMessage() != null && !exception.getMessage().isEmpty();
    }

    private Mono<Void> handlerServiceUnavailable(ServerWebExchange exchange, Throwable exception) {
        ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
                new String[]{PresentInternationalizationUtils.getMessage(GatewayExceptionKeys.GATEWAY_SERVICE_UNAVAILABLE,
                        new String[]{getNameMicroserviceTimeout(exception)})},
                HttpStatus.SERVICE_UNAVAILABLE,
                PresentDataUtils.getZonedDateTimeNow()
        );
        return writeErrorResponse(exchange, apiException);
    }

    private String getNameMicroserviceTimeout(Throwable exception) {
        if(exception.getMessage() != null){
            Pattern pattern = Pattern.compile("instance for\\s+[a-zA-Z\\-]+");
            Matcher matcher = pattern.matcher(exception.getMessage());

            if (matcher.find()) {
                return matcher.group(0).replace("instance for ", "");
            }
        }
        return PresentInternationalizationUtils.getMessage(BaseExceptionKeys.NAME_SERVICE_NOT_FOUND);
    }

    private Mono<Void> handlerGatewayTimeout(ServerWebExchange exchange) {
        ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
                new String[]{PresentInternationalizationUtils.getMessage(GatewayExceptionKeys.GATEWAY_READ_TIMEOUT)},
                HttpStatus.GATEWAY_TIMEOUT,
                PresentDataUtils.getZonedDateTimeNow()
        );
        return writeErrorResponse(exchange, apiException);
    }

    private Mono<Void> handlerThrowable(ServerWebExchange exchange, Throwable exception){
        ApiExceptionResponseDTO apiException = new ApiExceptionResponseDTO(
                new String[]{exception.getMessage()},
                HttpStatus.INTERNAL_SERVER_ERROR,
                PresentDataUtils.getZonedDateTimeNow()
        );
        return writeErrorResponse(exchange, apiException);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}