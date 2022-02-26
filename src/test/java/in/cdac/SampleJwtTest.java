package in.cdac;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class SampleJwtTest {
	
	static final String SEC_KEY = "9bb781da3050992302641dbe616454b6ca17feca36de49646a7c938eee52bfe0";
	
	// @Test
	void jwtGeneation() {
		
		String jwt =  Jwts.builder()
			.setSubject("rohit")
			.setIssuer("cdac")
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
			.signWith(SignatureAlgorithm.HS512, SEC_KEY)
			.compact();
		
		
		System.out.println(jwt);
		Assertions.assertNotNull(jwt);
		
	}
	
	
	// @Test
	void jwtValidation() {
		String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2hpdCIsImlzcyI6ImNkYWMiLCJpYXQiOjE2NDU4NTI5ODUsImV4cCI6MTY0NTg1MzA0NX0.UoLFzSKAOFhMLrSwOCvCpRCeylO-DLvFIznn97xcW-nU6I3MSwS_tgcYxwaLlcm_DB-yUt1qn9MGV2OckVgApA";
		// String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcmF0aG1lc2giLCJpc3MiOiJjZGFjIiwiaWF0IjoxNjQ1ODUyMjc3fQ.nJrqx5tkOQz-yu7-29CrxIkdd9BdaWAbwh2enX6T6Uk1n1ZXG2RdwbioPTPuh9bSpUCqiszh-7G7-1CjZ4DR8Q";
		
		Claims claim =  Jwts.parser()
			.setSigningKey(SEC_KEY)
			.parseClaimsJws(jwt)
			.getBody();
		
		System.out.println(claim.getSubject());
		
	}
	
}









