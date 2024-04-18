package br.com.present.auths.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import br.com.present.auths.filters.JWTAuthenticationFilter;
import br.com.present.auths.filters.JWTValidationFilter;
import br.com.present.auths.service.IKeyGeneratorService;
import br.com.present.auths.service.ITokenService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	public SecurityConfig(IKeyGeneratorService keyGeneratorService, ITokenService tokenService) {
		this.tokenService = tokenService;
		this.keyGeneratorService = keyGeneratorService;
	}

	private final IKeyGeneratorService keyGeneratorService;
	private final ITokenService tokenService;

	@Value("${security.path.login}")
	private static String pathLogin;
	
	@Value("${security.path.logout}")
	private static String pathLogout;
	
	//security.path.login: /login
	//security.path.logout: /logout
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.POST, pathLogin).permitAll()
				.anyRequest().authenticated()
		    )
			.addFilter(addTokenAuthenticationFilter())
			.addFilterAfter(addTokenValidationFilter(), JWTAuthenticationFilter.class)
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.logout(config -> config
				.logoutUrl(pathLogout)
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.logoutSuccessUrl(pathLogin)
			)
			.csrf(AbstractHttpConfigurer::disable)
			.build();
	}

	@Bean
	protected JWTAuthenticationFilter addTokenAuthenticationFilter() /*throws Exception */{
		var jwtAuthenticationFilter = new JWTAuthenticationFilter();
		/*jwtAuthenticationFilter.setAuthenticationManager(getAuthenticationManager());*/
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		jwtAuthenticationFilter.setPostOnly(true);
        return jwtAuthenticationFilter;
    }

	protected JWTValidationFilter addTokenValidationFilter() {
        var jwtValidationFilter = new JWTValidationFilter();
        this.tokenService.setJwtVerifier(keyGeneratorService.getAlgorithm());
        jwtValidationFilter.setTokenService(tokenService);
        return jwtValidationFilter;
    }
	
}
