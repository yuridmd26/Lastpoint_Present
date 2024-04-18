package br.com.present.auths.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.present.auths.config.constants.TokenConsts;
import br.com.present.auths.dto.UserDetailsImpl;
import br.com.present.auths.service.ITokenService;

@Service
public class TokenServiceImpl implements ITokenService {
	
	@Autowired
	private KeyGeneratorServiceImpl keyGeneratorService;
	
	private JWTVerifier jwtVerifier;

	@Override
	public String createAuthenticationToken(UserDetailsImpl userDetails, Instant currentTime, 
			Instant expiration, List<String> authorities) {
		
		return JWT.create()
                .withSubject((userDetails).getUsername())
                .withClaim(TokenConsts.ROLE_CLAIM_NAME, authorities)
                .withExpiresAt(Date.from(expiration))
                .withNotBefore(Date.from(currentTime))
                .withIssuedAt(Date.from(currentTime))
                .withIssuer(TokenConsts.ISSUER)
                .withJWTId(UUID.randomUUID().toString().replace("-", ""))
                .sign(keyGeneratorService.getAlgorithm());
	}

	@Override
	public String removePrefix(String token) {
		return token.replaceFirst(TokenConsts.HEADER_PREFIX, "");
    }

	@Override
	public DecodedJWT validateToken(String token) {
		return jwtVerifier.verify(token);
	}

	@Override
	public void setJwtVerifier(Algorithm algorithm) {
		jwtVerifier = JWT.require(algorithm).build();
    }
	
}