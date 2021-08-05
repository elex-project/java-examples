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
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

@Slf4j
public class JwtSample {
	private static final byte[] key;

	static {
		// HMACSHA256을 사용하므로 키의 길이는 32바이트이다.
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

	/**
	 *
	 * @param token 토큰
	 * @return
	 * @throws UnsupportedJwtException
	 * @throws MalformedJwtException
	 * @throws SignatureException
	 * @throws ExpiredJwtException
	 * @throws MissingClaimException
	 * @throws IncorrectClaimException
	 */
	public static Jws<Claims> parseToken(final String token)
			throws UnsupportedJwtException, MalformedJwtException, SignatureException, ExpiredJwtException,
			MissingClaimException, IncorrectClaimException {

		return Jwts.parserBuilder()
				.setSigningKey(key)
				.requireIssuer("Elex") // 토큰의 Issuer 일치 여부 확인
				.build()
				.parseClaimsJws(parseHeader(token));
	}

	/**
	 * Http 헤더에서 토큰 부분만 추출
	 * @param authenticationHeader http header
	 * @return 토큰 부분만 반환
	 */
	private static String parseHeader(final String authenticationHeader)
			throws MalformedJwtException {
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
		final String token = generateToken();
		System.out.println(token);

		final String authHeader = "Bearer " + token;
		Jws<Claims> claims = parseToken(authHeader);
		System.out.println(claims);
		final int userId = claims.getBody().get("userId", Integer.class);
		System.out.println("User Id: " + userId);
	}
}
