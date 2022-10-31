package com.maldo.backend.config.swagger;

import com.maldo.backend.config.security.JWTAuthenticationFilter;
import com.maldo.backend.config.security.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Configuration
@AllArgsConstructor
public class SwaggerConfig
{
	private final UserDetailsService userDetailsService;
	private final JWTAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception
	{
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		return http
				.csrf().disable()
				.authorizeRequests().anyRequest()
				.authenticated().and()
				.httpBasic().and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception
	{
		return http
				.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
	}

	public static void main(String[] args)
	{
		System.out.println("Password Token: " + new BCryptPasswordEncoder().encode("admin"));
	}

	/**         D E P R E C A T E D

	 @Bean UserDetailsService userDetailsService() {
	 InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	 manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin")).roles().build());
	 return manager;
	 }

	 protected void configure(HttpSecurity http) throws Exception
	 {
	 CorsConfiguration corsConfiguration = new CorsConfiguration();

	 corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
	 corsConfiguration.setAllowedOrigins(List.of("*"));
	 corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
	 corsConfiguration.setAllowCredentials(false);
	 corsConfiguration.setExposedHeaders(List.of("Authorization"));

	 // You can customize the following part based on your project, it's only a sample
	 http
	 .authorizeRequests()
	 .antMatchers("/**")
	 .permitAll()
	 .anyRequest()
	 .authenticated()
	 .and()
	 .csrf()
	 .disable()
	 .cors()
	 .configurationSource(request -> corsConfiguration);
	 }*/
}
