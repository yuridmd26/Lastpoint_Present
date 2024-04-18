package br.com.present.auths.filters;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.present.auths.config.constants.TokenConsts;
import br.com.present.auths.dto.AuthErrorResponseDTO;
import br.com.present.auths.service.ITokenService;
import br.com.present.commons.util.PresentJsonUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;

@Setter
public class JWTValidationFilter extends OncePerRequestFilter {
	
	private ITokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String contentHeaderAuth = request.getHeader(TokenConsts.AUTHORIZATION_HEADER);
		
		if(!isValidContentHeaderAuth(contentHeaderAuth)) {
			chain.doFilter(request, response);
            return;
		}

		var decodedJWT = tokenService.validateToken(contentHeaderAuth);
		if(decodedJWT == null) {
            handleResponseErrorAuth(response);
            return;
        }
		
		List<SimpleGrantedAuthority> roles = decodedJWT.getClaim(TokenConsts.ROLE_CLAIM_NAME).asList(SimpleGrantedAuthority.class);
		var usuario = new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, roles); 
		SecurityContextHolder.getContext().setAuthentication(usuario);
		chain.doFilter(request, response);
	}

	private boolean isValidContentHeaderAuth(String contentHeaderAuth) {
		return contentHeaderAuth == null || !contentHeaderAuth.startsWith(TokenConsts.HEADER_PREFIX);
	}

	private void handleResponseErrorAuth(HttpServletResponse response) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(TokenConsts.CONTENT_TYPE);
		response.getWriter().write(
			PresentJsonUtils.toJson(new AuthErrorResponseDTO(
					LocalDateTime.now().toLocalTime().toString(),
					HttpStatus.UNAUTHORIZED.value(), 
					HttpStatus.UNAUTHORIZED.getReasonPhrase(),
					"")
		));
	}
	
}