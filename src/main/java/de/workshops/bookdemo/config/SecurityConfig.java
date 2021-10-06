package de.workshops.bookdemo.config;

import static de.workshops.bookdemo.generated.Tables.USERS;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jooq.DSLContext;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DSLContext jooq;
	
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        authProvider.setUserDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// @formatter:off
				return jooq.select(USERS.fields())
						.from(USERS)
						.where(USERS.USERNAME.eq(username))
						.fetchSingleInto(de.workshops.bookdemo.user.User.class);
				// @formatter:on
			}
		});
        auth.authenticationProvider(authProvider);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
				.successHandler(new AuthenticationSuccessHandler() {
					
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						String username = authentication.getName();
						MDC.put("username", username);
						jooq.update(USERS)
							.set(USERS.LASTLOGIN, LocalDateTime.now())
							.where(USERS.USERNAME.eq(username))
							.execute();
						response.setStatus(HttpStatus.FOUND.value());
						response.setHeader("Location", "http://localhost:8080/booklist2");
					}
				})
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/h2-console/**");
		
	}
	
	
    
    
    
	
}
