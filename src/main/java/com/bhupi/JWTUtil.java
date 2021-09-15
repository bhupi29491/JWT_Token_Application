package com.bhupi;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
	
	public static String generateToken(String subject, String secretKey) {
		
	  return Jwts.builder() 
				.setId("Bhupi@9891")
				.setSubject(subject)
				.setIssuer("C & C Team")
				.setAudience("Security Team")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1)))
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(secretKey.getBytes()))
				.compact();
	}
	
	public static void main(String[] args) {
		String secretKey="TopSecret@203";
		String token = JWTUtil.generateToken("myToken", secretKey);
		System.out.println("Token :"+token);
		
		//Code to Parse the Token
		
		JwtParser parser = Jwts.parser();
		Claims claims = parser.setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
			  .parseClaimsJws(token)
			  .getBody();
		
		System.out.println("Token ID : "+claims.getId());
		System.out.println("Token Issued By : "+claims.getIssuer());
		System.out.println("Token Issued Time : "+claims.getIssuedAt());
		System.out.println("Token Expiry Time : "+claims.getExpiration());
		System.out.println("Token Audience : "+claims.getAudience());
	}
}
