package com.maldo.backend.config.swagger;

import com.maldo.backend.config.security.AuthManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SwaggerConfig
{
	/**
	 * (1) Disable Cross-Site Request Forgery (CSRF)
	 * (2) The user should be authenticated for any request in the application.
	 * (3) Spring Security will never create an HttpSession, and it will never use it to obtain the Security Context.
	 * (4) Spring Security’s HTTP Basic Authentication support is enabled by default.
	 *     However, as soon as any servlet-based configuration is provided, HTTP Basic must be explicitly provided.
	 *
	 *
	 *
	 *     Si usamos el AuthenticationManager a la vez que el securityChain, aun teniendo el
	 *     InMemoryUserDetailsManager, no vamos a acceder a la API
	 *     con las credenciales establecidas.
	 *
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http
				.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				.and()
				.cors().disable()
				.csrf(AbstractHttpConfigurer::disable) // (1)
				.authorizeRequests( auth -> auth
						.anyRequest().permitAll() // (2)
				)
				//.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // (3)
				//.httpBasic(Customizer.withDefaults()) // (4)
				.build();
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
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}


	@Bean
	CorsConfigurationSource corsConfigurationSource()
	{
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(false);
		configuration.setAllowedOrigins(List.of("*"));
		configuration.setAllowedMethods(List.of("*"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.addAllowedOriginPattern("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthenticationManager authenticationManagerBean()
	{
		return new AuthManager();
	}
}
