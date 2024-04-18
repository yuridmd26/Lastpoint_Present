package br.com.present.auths.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.present.auths.config.constants.TokenConsts;
import br.com.present.auths.config.exception.ApiKeyGenerateException;
import br.com.present.auths.service.impl.KeyGeneratorServiceImpl;

@Configuration
public class TokenConfig {
	
	@Bean
	public KeyGeneratorServiceImpl setGeneratorToken() throws ApiKeyGenerateException {
		var jwtKeyGenerator = new KeyGeneratorServiceImpl(TokenConsts.KEY_SIZE, false);
		jwtKeyGenerator.generateKey();
		return jwtKeyGenerator;
	}

}