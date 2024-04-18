package br.com.present.api.gateway.exception.keys;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class GatewayExceptionKeys {

	public static final String GATEWAY_CONNECT_TIMEOUT = "apiExceptionHandler.err.gatewayConnectTimeout";
	public static final String GATEWAY_READ_TIMEOUT = "apiExceptionHandler.err.gatewayReadTimeout";
	public static final String GATEWAY_SERVICE_UNAVAILABLE = "apiExceptionHandler.err.gatewayServiceUnavailable";
	public static final String GATEWAY_CONNECTION_REFUSED = "apiExceptionHandler.err.connectionRefused";

}
