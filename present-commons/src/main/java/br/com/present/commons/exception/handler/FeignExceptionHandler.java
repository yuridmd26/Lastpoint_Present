package br.com.present.commons.exception.handler;

import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.commons.exception.keys.BaseExceptionKeys;
import br.com.present.commons.util.PresentDataUtils;
import br.com.present.commons.util.PresentInternationalizationUtils;
import br.com.present.commons.util.PresentJsonUtils;
import feign.FeignException;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class FeignExceptionHandler {

    public static ResponseEntity<ApiExceptionResponseDTO> handleFeignException(FeignException exception) {
        ApiExceptionResponseDTO responseDto;

        responseDto = handleWhenServiceNotAvailable(exception);
        responseDto = handleReadTimeout(responseDto, exception);
        responseDto = handleConnectTimeout(responseDto, exception);
        responseDto = createDefaultResponse(responseDto, exception);

        return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
    }

    private static ApiExceptionResponseDTO handleWhenServiceNotAvailable(FeignException exception){
        if(isServiceUnavailable(exception)){
            String jsonError = exception.responseBody().map(PresentJsonUtils::toJson).orElse("");
            String nameService = extractPresentService(jsonError);

            if(nameService != null){
                return new ApiExceptionResponseDTO(
                    new String[]{PresentInternationalizationUtils.getMessage(
                            BaseExceptionKeys.SERVICE_UNAVAILABLE, new String[]{nameService})},
                    HttpStatus.SERVICE_UNAVAILABLE,
                    PresentDataUtils.getZonedDateTimeNow()
                );
            }
        }
        return null;
    }

    private static boolean isServiceUnavailable(FeignException exception){
        return exception.status() > 0 && HttpStatus.SERVICE_UNAVAILABLE.
                equals(HttpStatus.valueOf(exception.status()));
    }

    private static String extractPresentService(String jsonError) {
        String regex = "present-([a-zA-Z]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(jsonError);

        if (matcher.find()) {
            return "present-"+matcher.group(1);
        }
        return null;
    }

    private static ApiExceptionResponseDTO handleReadTimeout(ApiExceptionResponseDTO responseDto, FeignException exception) {
        if(responseDto == null && isReadTimeout(exception)){
            return new ApiExceptionResponseDTO(
                new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.REQUEST_READ_TIMEOUT,
                        new String[]{getNameMicroserviceTimeout(exception)})},
                HttpStatus.REQUEST_TIMEOUT,
                PresentDataUtils.getZonedDateTimeNow()
            );
        }
        return responseDto;
    }

    private static boolean isReadTimeout(FeignException exception) {
        if(isMessageFilled(exception)){
            Pattern pattern = Pattern.compile("Read timed");
            Matcher matcher = pattern.matcher(exception.getMessage());

            return matcher.find();
        }
        return false;
    }

    private static ApiExceptionResponseDTO handleConnectTimeout(ApiExceptionResponseDTO responseDto, FeignException exception) {
        if(responseDto == null && isConnectTimeout(exception)){
            return new ApiExceptionResponseDTO(
                    new String[]{PresentInternationalizationUtils.getMessage(BaseExceptionKeys.REQUEST_CONNECT_TIMEOUT,
                            new String[]{getNameMicroserviceTimeout(exception)})},
                    HttpStatus.GATEWAY_TIMEOUT,
                    PresentDataUtils.getZonedDateTimeNow()
            );
        }
        return responseDto;
    }

    private static boolean isConnectTimeout(FeignException exception) {
        if(isMessageFilled(exception)){
            Pattern pattern = Pattern.compile("Connect timed");
            Matcher matcher = pattern.matcher(exception.getMessage());

            return matcher.find();
        }
        return false;
    }

    private static String getNameMicroserviceTimeout(FeignException exception) {
        if(exception.request() != null && exception.request().url() != null){
            try {
                URI url = new URI(exception.request().url());
                return url.getHost() != null ? url.getHost() : PresentInternationalizationUtils.
                        getMessage(BaseExceptionKeys.NAME_SERVICE_NOT_FOUND);
            } catch (URISyntaxException e) {
                return PresentInternationalizationUtils.getMessage(BaseExceptionKeys.NAME_SERVICE_NOT_FOUND);
            }
        }
        return PresentInternationalizationUtils.getMessage(BaseExceptionKeys.NAME_SERVICE_NOT_FOUND);
    }

    private static ApiExceptionResponseDTO createDefaultResponse(ApiExceptionResponseDTO responseDto, FeignException exception) {
        if(responseDto == null){
            return exception.responseBody()
                    .map(body -> createDefaultWhenInformedBody(exception, body))
                    .orElseGet(() -> createDefaultWhenNotInformedBody(exception));
        }
        return responseDto;
    }

    private static ApiExceptionResponseDTO createDefaultWhenInformedBody(FeignException exception, ByteBuffer body) {
        ApiExceptionResponseDTO responseDTO = PresentJsonUtils.fromJson(body, ApiExceptionResponseDTO.class);
        if(isMessageComingFromAnotherMicroservice(responseDTO)){
            return responseDTO;
        }
        return createDefaultWhenNotInformedBody(exception);
    }

    private static boolean isMessageComingFromAnotherMicroservice(ApiExceptionResponseDTO responseDto) {
        return responseDto.getMessages() != null && responseDto.getMessages().length > 0 &&
                responseDto.getTimestamp() != null && responseDto.getHttpStatus() != null;
    }

    private static ApiExceptionResponseDTO createDefaultWhenNotInformedBody(FeignException exception) {
        return new ApiExceptionResponseDTO(
            new String[]{getMessageFeignException(exception)},
            getHttpStatusFeignException(exception),
            PresentDataUtils.getZonedDateTimeNow()
        );
    }

    private static String getMessageFeignException(FeignException exception) {
        return PresentInternationalizationUtils.getMessage(BaseExceptionKeys.TITLE_FEIGN) + " " +
                (isMessageFilled(exception) ? exception.getMessage() : PresentInternationalizationUtils
                        .getMessage(BaseExceptionKeys.COMPLEMENT_FEIGN));
    }

    private static HttpStatus getHttpStatusFeignException(FeignException exception) {
        return isMessageFilled(exception) && exception.status() > 0 ?
                HttpStatus.valueOf(exception.status()) : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private static boolean isMessageFilled(FeignException exception) {
        return exception.getMessage() != null && !exception.getMessage().isEmpty();
    }
}
