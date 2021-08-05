/*
 * Examples for Java
 *
 * Copyright (c) 2021. Elex. All Rights Reserved.
 * https://www.elex-project.com/
 */

package kr.pe.elex.examples;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

public class JwtSample {
	private static final byte[] key;

	static {
		key = new byte[32];
		new Random().nextBytes(key);
	}

	public static String generateToken() throws InvalidKeyException {
		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setIssuer("Elex")
				.setExpiration(Date.from(Instant.now().plus(3, ChronoUnit.HOURS)))
				.claim("userId", 3)
				.signWith(Keys.hmacShaKeyFor(key))
				.compact();
	}

	public static Jws<Claims> parseToken(final String token)
			throws UnsupportedJwtException, MalformedJwtException, SignatureException, ExpiredJwtException {

		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(parseHeader(token));
	}

	private static String parseHeader(final String authenticationHeader) {
		final String[] authentication = authenticationHeader.split(" ");
		if (authentication.length == 2 && authentication[0].matches("[bB]earer")) {
			return authentication[1];
		} else if (authentication.length == 1) {
			return authentication[0];
		} else {
			throw new MalformedJwtException("Authentication Header param must be started with 'Bearer ': " + authenticationHeader);
		}
	}

	public static void main(String... args) {
		String token = generateToken();
		System.out.println(token);

		String authHeader = "Bearer " + token;
		Jws<Claims> claims = parseToken(authHeader);
		System.out.println(claims);
	}
}
