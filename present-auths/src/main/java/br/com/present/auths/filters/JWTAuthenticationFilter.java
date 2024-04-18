package br.com.present.auths.filters;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.present.auths.config.constants.TokenConsts;
import br.com.present.auths.config.exception.ApiAuthenticationException;
import br.com.present.auths.dto.JwtResponseDTO;
import br.com.present.auths.dto.UserDTO;
import br.com.present.auths.dto.UserDetailsImpl;
import br.com.present.auths.service.ITokenService;
import br.com.present.commons.util.PresentCollectionUtils;
import br.com.present.commons.util.PresentJsonUtils;
import br.com.present.commons.util.PresentStreamUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private ITokenService tokenJWTService;
	
	@Autowired
	private UserDetailsService detalheUsuarioService;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws ApiAuthenticationException {
		if(!request.getMethod().equals(HttpMethod.POST.toString())) {
			throw new ApiAuthenticationException("", new Object[] {request.getMethod()});
		}

		UserDTO userDto;
        try {
        	String body = request.getReader().lines().collect(PresentCollectionUtils.joining(System.lineSeparator()));
        	userDto = PresentJsonUtils.fromJson(body, UserDTO.class);
        } catch (Exception e) {
            throw new ApiAuthenticationException("");
        }

        var userDetails = detalheUsuarioService.loadUserByUsername(userDto.getLogin());
        var authRequest = new UsernamePasswordAuthenticationToken(userDto.getLogin().trim(), 
        		userDto.getSenha(), userDetails.getAuthorities());

        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
											FilterChain chain, Authentication authResult) throws IOException, ServletException {
		Instant currentTime = Instant.now();
		Long expirationMinutes = TokenConsts.EXPIRATION_TIME_IN_MINUTES;
		Instant expirationToken = currentTime.plus(expirationMinutes, ChronoUnit.MINUTES);
		 
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		
		List<String> authorities = new ArrayList<>();
		PresentStreamUtils.collectionToStream(userDetails.getAuthorities())
					   .forEach(grant -> authorities.add(grant.getAuthority()));
		
		String token = tokenJWTService.createAuthenticationToken(userDetails, currentTime, expirationToken, authorities);
		response.setHeader(TokenConsts.AUTHORIZATION_HEADER, TokenConsts.HEADER_PREFIX + token);
		response.getWriter().write(PresentJsonUtils.toJson(new JwtResponseDTO(token)));
		response.setContentType(TokenConsts.CONTENT_TYPE);
	}
}