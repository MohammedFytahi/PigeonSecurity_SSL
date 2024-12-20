package net.yc.race.track.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure HttpSecurity for OAuth2 Resource Server with Keycloak
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
                .sessionManagement(session -> session.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS)) // Stateless session management
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/login").permitAll() // Public endpoints
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Restricted to ADMIN role
                        .requestMatchers("/organizer/**").hasRole("ORGANIZER") // Restricted to ORGANIZER role
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)) // 401 for unauthorized access
                        .accessDeniedHandler(accessDeniedHandler()) // 403 for forbidden access
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()) // Configure JWT decoding and role mapping
                        )
                )
                .httpBasic(httpBasic -> httpBasic.realmName("PigeonSkyRace")); // Optional HTTP Basic Auth realm

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Access Denied\", \"message\": \"You do not have permission to access this resource.\"}");
        };
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
             Object realmAccessClaim = jwt.getClaims().get("realm_access");

             if (realmAccessClaim instanceof Map<?, ?>) {
                Map<?, ?> realmAccessMap = (Map<?, ?>) realmAccessClaim;
                Object roles = realmAccessMap.get("roles");

                 if (roles instanceof List<?>) {
                    List<?> rolesList = (List<?>) roles;
                    return rolesList.stream()
                            .filter(role -> role instanceof String)
                            .map(role -> new SimpleGrantedAuthority((String) role))
                            .collect(Collectors.toList());
                }
            }

             return List.of();
        });
        return converter;
    }

}
