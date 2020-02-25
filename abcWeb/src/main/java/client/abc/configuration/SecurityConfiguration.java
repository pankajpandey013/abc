package client.abc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security Configuration Bean
 * @author pankajpandey013
 *
 */
@Configuration
@EnableWebSecurity

@ComponentScan(basePackageClasses = CustomAuthenticationProvider.class)

public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
    CustomAuthenticationProvider authenticationProvider;
	
    @Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
	        .authorizeRequests()
	            .antMatchers("/resources/**").permitAll()
	            .antMatchers("/login*").permitAll()
	            .anyRequest().authenticated() // every user must be authenticated
	            .and()
	        .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
	        .loginProcessingUrl("/doLogin")
	        .successForwardUrl("/postLogin")
            .failureUrl("/loginFailed");
	    
	    httpSecurity.csrf().disable();
	}
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authenticationProvider);

    	
    }
}
