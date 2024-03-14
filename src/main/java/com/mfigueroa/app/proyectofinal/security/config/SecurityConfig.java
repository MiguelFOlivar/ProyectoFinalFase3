package com.mfigueroa.app.proyectofinal.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.mfigueroa.app.proyectofinal.security.filter.JWTFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
	private final JWTFilter jwtFilter;
	private final AuthenticationProvider authenticationProvider;
	@Bean
	SecurityFilterChain securityFilterChain( HttpSecurity security) throws Exception {
		security.csrf( csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> auth.requestMatchers(getPublicEndPoints()).permitAll()
				.anyRequest().authenticated())
		.sessionManagement( sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return security.build();
	}

	
	private RequestMatcher getPublicEndPoints() {
		return new OrRequestMatcher(
				new AntPathRequestMatcher("api/auth/**")
				);
	}
}
