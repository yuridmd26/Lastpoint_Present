package br.com.present.auths.service;

import java.time.Instant;
import java.util.List;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.present.auths.dto.UserDetailsImpl;

public interface ITokenService {

	void setJwtVerifier(Algorithm algorithm);
	String createAuthenticationToken(UserDetailsImpl userDetails, Instant currentTime, Instant expiration, List<String> authorities);
	DecodedJWT validateToken(String token);
	String removePrefix(String token);
	
}