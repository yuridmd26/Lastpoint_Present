package br.com.present.auths.config.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenConsts { 
	
	public static final String HEADER_PREFIX = "Bearer ";
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final Long REMEMBER_DAYS = 10L;
	public static final Long EXPIRATION_TIME_IN_MINUTES = 1L;
	public static final String ISSUER = "present-auths";
	public static final String ROLE_CLAIM_NAME = "roles";
	public static final String CONTENT_TYPE = "application/json";
	public static final Integer KEY_SIZE = 2048;
	
}