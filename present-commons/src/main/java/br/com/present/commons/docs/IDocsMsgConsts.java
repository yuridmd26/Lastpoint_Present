package br.com.present.commons.docs;

import lombok.experimental.UtilityClass;

public interface IDocsMsgConsts {

	String DEFAULT_TITLE = "Present Services (Api)";
	String DEFAULT_DESCRIPTION = "Application for class calls";
	String DEFAULT_VERSION = "N/A";

	@UtilityClass
	class Server {
		public static final String DEFAULT_NAME = "";
	}
	
	@UtilityClass
	class Licence {
		public static final String NAME = "Apache License Version 2.0";
		public static final String URL = "https://www.apache.org/licenses/LICENSE-2.0";
	}
	
	@UtilityClass
	class Contact {
		public static final String SITE = "";
		public static final String NAME = "Equipe Present";
		public static final String EMAIL = "brenoaugustocomandolli@gmail.com";
	}

	@UtilityClass
	class MediaType {
		public static final String APP_JSON = "Application/json";
	}

	@UtilityClass
	class Responses {
	    public static final String MSG_100 = "Provisional Response - the server will send a final response after the request is completed";
	    public static final String MSG_101 = "Switching Protocols - indicates which protocol the server is switching to";
	    public static final String MSG_102 = "Processing - the request is being processed, and no response is available yet";
	    public static final String MSG_103 = "Early Hints - indicates that the agent should start preloading resources while the server prepares a response";

	    public static final String MSG_200 = "OK - the request has been successfully processed";
	    public static final String MSG_201 = "Created - resource created within a collection or database";
	    public static final String MSG_202 = "Accepted - the request has been received, but no action has been taken on it yet";
	    public static final String MSG_203 = "Non-Authoritative Information - the request has been successfully processed, and there is no additional content in the response";
	    public static final String MSG_204 = "No Content - the server has successfully fulfilled the request, and there is no additional content to be sent in the response";
	    public static final String MSG_205 = "Reset Content - reset the document view for this response";
	    public static final String MSG_206 = "Partial Content - respond with multiple sequences, splitting the download into several streams";
	    public static final String MSG_207 = "Multi-Status - conveys information about multiple resources in situations where multiple status codes might be appropriate";
	    public static final String MSG_208 = "Already Reported - avoids enumerating the internal members of multiple bindings to the same collection repeatedly";
	    public static final String MSG_226 = "IM Used - the server has fulfilled a GET request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance";

	    public static final String MSG_300 = "Multiple Choices - the request has more than one possible response, and the user must choose one";
	    public static final String MSG_301 = "Moved Permanently - the requested resource's URI has changed, and the new URI will likely be specified in the response";
	    public static final String MSG_302 = "Found - the requested resource's URI has changed temporarily, and further changes in the URI may be made in the future";
	    public static final String MSG_303 = "See Other - the server sends this response to instruct the client to retrieve the requested resource at another URI with a GET request";
	    public static final String MSG_304 = "Not Modified - the response has not been modified since the last time it was requested, and the client can use its cached version";
	    public static final String MSG_305 = "Use Proxy - it was defined in a previous version of the HTTP specification to indicate that a response should be accessed by a proxy";
	    public static final String MSG_306 = "Unused - this response code is no longer used, it is reserved, and was used in a previous version";
	    public static final String MSG_307 = "Temporary Redirect - the server sends this response by directing the client to get the requested resource at another URI with the same method used in the original request";
	    public static final String MSG_308 = "Permanent Redirect - the resource is now permanently located at another URI";

	    public static final String MSG_400 = "Bad Request - the request does not conform to the expected format";
	    public static final String MSG_401 = "Unauthorized - authentication data is incorrect";
	    public static final String MSG_403 = "Forbidden - access permission error";
	    public static final String MSG_404 = "Not Found - you are trying to access a resource that does not exist";
	    public static final String MSG_405 = "Method Not Allowed - the request method is known to the server but has been disabled and cannot be used";
	    public static final String MSG_406 = "Not Acceptable - does not support the data format specified in the header";
	    public static final String MSG_407 = "Proxy Authentication Required - authentication data is incorrect, but authentication is required by a proxy";
	    public static final String MSG_408 = "Request Timeout - the server would like to drop this unused connection";
	    public static final String MSG_409 = "Conflict - the request conflicts with the current state of the server";
	    public static final String MSG_410 = "Gone - the requested content has been permanently deleted from the server without a forwarding address";
	    public static final String MSG_411 = "Length Required - the server rejected the request because the Content-Length field in the header is not defined and is required by the server";
	    public static final String MSG_412 = "Precondition Failed - the client indicated preconditions in its headers that the server does not meet";
	    public static final String MSG_413 = "Payload Too Large - the request entity is larger than the limits defined by the server";
	    public static final String MSG_414 = "URI Too Long - the URI requested by the client is longer than the server is willing to interpret";
	    public static final String MSG_415 = "Unsupported Media Type - cannot process the data sent due to its format";
	    public static final String MSG_416 = "Range Not Satisfiable - the specified range in the Range header field of the request cannot be fulfilled; it might be outside the size of the target URI's data";
	    public static final String MSG_417 = "Expectation Failed - the expectation indicated by the Expect field of the header cannot be met by the server";
	    public static final String MSG_421 = "Misdirected Request - the request was directed at a server that is not able to produce a response";
	    public static final String MSG_422 = "Unprocessable Entity - semantic error, data violates some business rule";
	    public static final String MSG_423 = "Locked - the resource being accessed is locked";
	    public static final String MSG_424 = "Failed Dependency - the request failed due to a previous request failure";
	    public static final String MSG_425 = "Too Early - the server is unwilling to risk processing a request that might be replayed";
	    public static final String MSG_426 = "Upgrade Required - the server refuses to perform the request using the current protocol but is willing to perform it after the client upgrades to a different protocol";
	    public static final String MSG_428 = "Precondition Required - the origin server requires the request to be conditional";
	    public static final String MSG_429 = "Too Many Requests - rate limit exceeded";
	    public static final String MSG_431 = "Request Header Fields Too Large - the server is unwilling to process the request because the header fields are too large";
	    public static final String MSG_451 = "Unavailable For Legal Reasons - the user has requested a resource that is illegal, such as a censored page by a government";

	    public static final String MSG_500 = "Internal Server Error - an internal server error has occurred";
	    public static final String MSG_501 = "Not Implemented - the request method is not supported by the server and cannot be handled";
	    public static final String MSG_502 = "Bad Gateway - when working as a gateway to get a response needed to handle the request, the server received an invalid response";
	    public static final String MSG_503 = "Service Unavailable - common causes are server maintenance or being overloaded";
	    public static final String MSG_504 = "Gateway Timeout - the request took too long and cannot be processed";
	    public static final String MSG_505 = "HTTP Version Not Supported - the HTTP version used in the request is not supported by the server";
	    public static final String MSG_506 = "Variant Also Negotiates - the server has an internal configuration error";
	    public static final String MSG_507 = "Insufficient Storage - the server detected an infinite loop while processing the request";
	    public static final String MSG_508 = "Loop Detected - the server detected an infinite loop while processing the request";
	    public static final String MSG_510 = "Not Extended - further extensions to the request are required for the server to fulfill it";
	    public static final String MSG_511 = "Network Authentication Required - the client must authenticate to gain network access";
	}
}
