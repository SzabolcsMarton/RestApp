package com.szabolcs.rest.RestApp.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.szabolcs.rest.RestApp.services.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final JpaUserDetailsService jpaUserDetailsService ;
    
    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
	this.jpaUserDetailsService = jpaUserDetailsService;
    }



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	return http.csrf().disable()
		.authorizeRequests(auth -> {auth
			.antMatchers(HttpMethod.POST, "/api/registration/emp").permitAll()
			.antMatchers("/api/manager/*").hasRole("MANAGER")
			.anyRequest().authenticated();
			//.anyRequest().permitAll();
		})
		.userDetailsService(jpaUserDetailsService)
		//.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults())
		.build();
    }
    
    //@SuppressWarnings("deprecation")
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
	//return NoOpPasswordEncoder.getInstance();
    }
    
//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { 
//        return authenticationConfiguration.getAuthenticationManager();
//    }
    
    

}
