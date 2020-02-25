package client.abc.configuration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired 
	HttpSession session;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		try {
			// trying to authenticate user
		} catch (Exception e) {
			//log.warn("log in failed for user: " + username + "\n " + e.getMessage());
		}
		return authentication;
		
	}
	@Override
	public boolean supports(Class<?>aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}
