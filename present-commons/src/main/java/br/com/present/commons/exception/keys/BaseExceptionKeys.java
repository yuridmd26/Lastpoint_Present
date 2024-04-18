package br.com.present.commons.exception.keys;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class BaseExceptionKeys {
	
	public static final String METHOD_NOT_ALLOWED = "apiExceptionHandler.err.methodNotAllowed";
	public static final String TYPE_MISMATCH = "apiExceptionHandler.err.typeMismatch";
	public static final String MEDIA_TYPE_NOT_SUPPORTED = "apiExceptionHandler.err.mediaTypeNotSupported";
	public static final String MESSAGE_NOT_READABLE = "apiExceptionHandler.err.messageNotReadable";
	public static final String TITLE_FEIGN = "apiExceptionHandler.err.titleFeignException";
	public static final String COMPLEMENT_FEIGN = "apiExceptionHandler.err.complementFeignException";
	public static final String SERVICE_UNAVAILABLE = "apiExceptionHandler.err.serviceUnavailable";
	public static final String REQUEST_READ_TIMEOUT = "apiExceptionHandler.err.requestReadTimeout";
	public static final String REQUEST_CONNECT_TIMEOUT = "apiExceptionHandler.err.requestConnectTimeout";
	public static final String NAME_SERVICE_NOT_FOUND = "apiExceptionHandler.err.nameServiceNotFound";
	public static final String QUERY_TIMEOUT = "apiExceptionHandler.err.queryTimeout";
	public static final String SQL_CONNECT_TIMEOUT = "apiExceptionHandler.err.sqlConnectTimeout";
    public static final String MISSING_SERVLET_REQUEST = "apiExceptionHandler.err.missingServletRequest";
	public static final String JSON_CONVERT_ERROR = "apiExceptionHandler.err.jsonConvertError";
	public static final String AMQP_CONNECT_ERROR = "apiExceptionHandler.err.AmqpConnectError";
	public static final String AMQP_ERROR = "apiExceptionHandler.err.AmqpError";

}