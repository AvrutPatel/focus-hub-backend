package com.app.time_tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. Tell Spring Security to use our custom CORS configuration below
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // 2. Disable CSRF (standard for stateless REST APIs)
                .csrf(csrf -> csrf.disable())
                // 3. Configure route access
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/tasks/**").permitAll()
                        .requestMatchers("/api/time-logs/**").permitAll()
                        .requestMatchers("/api/notes/**").permitAll()// Temporarily open tasks so we can develop the UI without JWTs
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    // This creates a global CORS policy that allows your Vite frontend to connect
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Locked down to your specific frontend URL
        configuration.setAllowedOrigins(Arrays.asList("https://focus-hub-frontend.vercel.app"));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}