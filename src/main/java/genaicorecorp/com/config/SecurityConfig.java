//package genaicorecorp.com.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    // Password encoder bean (BCrypt)
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // Allow non-secured paths (for API endpoints)
//    @Bean
//    WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/api/users/register", "/api/users/delete"); // Allow /register and /delete without security
//    }
//
//    @SuppressWarnings("removal")
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()  // Disable CSRF for stateless APIs like /delete (optional, but needed for DELETE requests)
//            .authorizeHttpRequests(authz -> authz
//                .requestMatchers(HttpMethod.POST, "/api/users/register", "/api/users/delete").permitAll() // Allow POST and DELETE requests for these endpoints
//                .anyRequest().authenticated() // Require authentication for other requests
//            )
//            .formLogin().permitAll(); // Use form-based login for non-API routes, no need to disable httpBasic()
//
//        return http.build();
//    }
//}
//

package genaicorecorp.com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

/**
 * SecurityConfig class to configure Spring Security.
 * - Disables CSRF for REST APIs
 * - Enables CORS for cross-origin requests
 * - Uses stateless session management
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * Bean for password encryption using BCrypt.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Security filter chain configuration.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Initializing Security Configuration...");

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
            .csrf(csrf -> csrf.disable()) 
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST, "/api/users/**").permitAll() 
                .requestMatchers(HttpMethod.DELETE, "/api/users/**").permitAll() 
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
                .requestMatchers(HttpMethod.GET, "/api/users/**").authenticated() 
                .requestMatchers(HttpMethod.POST, "/api/user/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/user/**").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/api/user/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/user/**").authenticated()
                .requestMatchers(HttpMethod.HEAD, "/api/user/**").authenticated()
                .requestMatchers(HttpMethod.OPTIONS, "/api/user/**").authenticated()
                .anyRequest().denyAll() 
            );

        logger.info("Security Configuration Successfully Loaded.");
        return http.build();
    }

    /**
     * CORS configuration to allow frontend applications to access the APIs.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://yourfrontenddomain.com"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
