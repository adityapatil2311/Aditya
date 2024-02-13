package com.vehicle.vehicalmanagement.appsecurity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

	@Bean
	public UserDetailsService detailsService(PasswordEncoder encoder) {
		UserDetails details=User.withUsername("Aditya").password(encoder.encode("Adi123")).roles("Admin").build();
		return new InMemoryUserDetailsManager(details);
		
	}
	@Bean
	public SecurityFilterChain AppSecurityConfig(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/api/post").permitAll()
				.and().
				authorizeHttpRequests().requestMatchers("/api/**")
				.authenticated()
				.anyRequest().authenticated()
				.and()
				//requestMatchers("/swagger-ui.html/**")
				
				.formLogin().and().build();
		
	}
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
		
	}
}
