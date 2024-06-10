package com.menuchallenge.challenge08.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.menuchallenge.challenge08.dto.responses.JwTClaims;
import com.menuchallenge.challenge08.entity.User;
import com.menuchallenge.challenge08.service.JwTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class JwTServiceImplementation implements JwTService {

	private final String JWT_SECRET;
	private final String ISSUER;
	private final long JWT_EXPIRATION;

	public JwTServiceImplementation(
			@Value("${challenge_08.jwt.secret_key}") String jwtSecret,
			@Value("${challenge_08.jwt.issuer}") String issuer,
			@Value("${challenge_08.jwt.expirationInSecond}") long expiration
	){
		this.JWT_EXPIRATION= expiration;
		this.JWT_SECRET= jwtSecret;
		this.ISSUER = issuer;
	}

	@Override
	public String generateToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
			return JWT.create()
					.withSubject(user.getId())
					.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
					.withIssuedAt(Instant.now())
					.withExpiresAt(Instant.now().plusSeconds(JWT_EXPIRATION))
					.sign(algorithm);
			}catch (JWTCreationException exception){
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error while create jwt token");
			}
		}

	@Override
	public boolean verifyJwtTokem(String bearerToken) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(ISSUER)
					.build();
			String token = parseJwt(bearerToken);
			verifier.verify(token);
			return true;
		}catch (JWTCreationException exception){
			return false;
		}

	}

	@Override
	public JwTClaims getClaimsByToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(ISSUER)
					.build();
			DecodedJWT decodedJWT = verifier.verify(parseJwt(token));
			return JwTClaims.builder()
					.userAccountId(decodedJWT.getSubject())
					.roles(decodedJWT.getClaim("roles").asList(String.class))
					.build();
		}catch (JWTVerificationException exception){
			return null;
		}
	}

	private String parseJwt(String token){
		if(token != null && token.startsWith("Bearer ")){
			return token.substring(7);
		}
		return null;
	}
}
