package in.cdac.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtFilter extends OncePerRequestFilter {
	
	static final String SEC_KEY = "9bb781da3050992302641dbe616454b6ca17feca36de49646a7c938eee52bfe0";
	
	@Autowired
	AppUserDetailsService appUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null) {
			
			String jwt = authHeader.substring(7);
			System.out.println(jwt);
			
			
			Claims claim =  Jwts.parser()
					.setSigningKey(SEC_KEY)
					.parseClaimsJws(jwt)
					.getBody();
			
			
			if(claim != null && claim.getSubject() != null && claim.getExpiration() != null && claim.getExpiration().after(new Date())) {
				
				String username = claim.getSubject();
				
				
                UserDetails userDetails =  appUserDetailsService.loadUserByUsername(username);

                if(userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    // settting the auth information
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
			}
			
			
		}
		
		
		
		
		filterChain.doFilter(request, response);
	}

}
