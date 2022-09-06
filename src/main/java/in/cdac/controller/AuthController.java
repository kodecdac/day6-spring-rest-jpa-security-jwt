package in.cdac.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cdac.model.AuthRequestModel;
import in.cdac.model.AuthResponseModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
	static final String SEC_KEY = "9bb781da3050992302641dbe616454b6ca17feca36de49646a7c938eee52bfe0";

	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody @Valid AuthRequestModel input) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));
		
		
		String jwt =  Jwts.builder()
				.setSubject(input.getUsername())
				.setIssuer("cdac")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS512, SEC_KEY)
				.compact();
		
		AuthResponseModel respones = new AuthResponseModel();
		respones.setJwt(jwt);
		
		return new ResponseEntity<>(respones, HttpStatus.OK);
	}
	
	
	
	// @PostMapping("/genreate")
	public ResponseEntity<?> generateToken() {
		String jwt =  Jwts.builder()
				.setSubject("admin")
				.setIssuer("cdac")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS512, SEC_KEY)
				.compact();
		
		return new ResponseEntity<>(jwt, HttpStatus.OK);
	}
	
}



