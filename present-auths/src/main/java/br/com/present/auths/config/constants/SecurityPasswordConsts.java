package br.com.present.auths.config.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityPasswordConsts {

	public static final Integer MIN_LENGTH_LOGIN = 10;
	public static final Integer MIN_LENGTH_PASSWORD = 5;
	public static final Integer MAX_LENGTH_LOGIN = 45;
	public static final Integer MAX_LENGTH_PASSWORD = 32;
	public static final Integer COUNT_MIN_NUMERIC = 2;
	public static final Integer COUNT_MIN_UPPERCASE = 1;
	public static final Integer COUNT_MIN_LOWERCASE = 1;
	public static final Integer COUNT_MIN_SYMBOLS = 1;
	public static final Integer COUNT_ATTEMPTS_GENERATION_HASH = 3;
	    
}