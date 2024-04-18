package br.com.present.auths.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

	@Bean
	public PasswordEncoder getPasswordEndocoder() {
		return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
	}
}