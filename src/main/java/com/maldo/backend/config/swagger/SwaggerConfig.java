package com.maldo.backend.config.swagger;

import com.maldo.backend.config.security.AuthManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SwaggerConfig extends WebSecurityConfigurerAdapter
{
	/**
	 * A parte de WebSecurityConfigurerAdapter, esté deprecado, para un entorno de desarrollo
	 * ese método funciona. Da menos problems de configuración que el nuevo SecurityFilterChain.
	 *
	 * No usar con fines de producción.
	 *
	 * @param http the {@link HttpSecurity} to modify
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
		corsConfiguration.setAllowedOrigins(List.of("*"));
		corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
		corsConfiguration.setAllowCredentials(false);
		corsConfiguration.setExposedHeaders(List.of("Authorization"));

		// You can customize the following part based on your project, it's only a sample
		http.authorizeRequests().antMatchers("/**").permitAll().anyRequest()
				.authenticated().and().csrf().disable().cors().configurationSource(request -> corsConfiguration);
	}

	@Bean
	public InMemoryUserDetailsManager users()
	{
		return new InMemoryUserDetailsManager(
				User.withUsername("Maldo601")
					.password(passwordEncoder().encode("admin"))
					.authorities("read")
					.build()
		);
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean()
	{
		return new AuthManager();
	}
	@Bean
	PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
