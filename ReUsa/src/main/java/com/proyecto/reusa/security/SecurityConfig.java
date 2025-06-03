package com.proyecto.reusa.security;

import com.proyecto.reusa.exceptions.CustomException;
import com.proyecto.reusa.models.Token;
import com.proyecto.reusa.models.Usuario;
import com.proyecto.reusa.models.repositories.TokenRepository;
import com.proyecto.reusa.models.repositories.UserRepository;
import com.proyecto.reusa.services.auth.JwtService;
import com.proyecto.reusa.services.auth.Service_auth;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RequestCachingFilter requestCachingFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers("/auth/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/products/")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/products/imageProduct")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET, "/products/{id_product}")
                                .permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(requestCachingFilter, HeaderWriterFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/auth/logout")
                                .addLogoutHandler((request, response, authentication) -> {
                                    final var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                                    logout(authHeader);
                                })
                                .logoutSuccessHandler(((request, response, authentication) ->
                                        SecurityContextHolder.clearContext()))
                );
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permite el origen de tu frontend. ¡Importante! Reemplaza con el puerto real de tu frontend.
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.OPTIONS.name()
        ));
        configuration.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT
        ));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    private void logout(final String token){
        if(token == null || !token.startsWith("Bearer ")){
            throw new IllegalArgumentException("Token no válido");
        }

        final String jwtToken = token.substring(7);
        final String userEmail = jwtService.extractUsername(jwtToken);
        Optional<Usuario> user = userRepository.getUsuarioByEmail(userEmail);
        if(user.isEmpty()){
            throw new IllegalArgumentException("Usuario no válido");
        }
        final Optional<Token> foundToken = tokenRepository.getTokenByUsuarioAndExpiredAndRevoked(user.get(), false, false);
        if(foundToken.isEmpty() || foundToken.get().getRevoked()){
            throw new IllegalArgumentException("Token no válido");
        }
        foundToken.get().setExpired(true);
        foundToken.get().setRevoked(true);
        tokenRepository.save(foundToken.get());


    }
}
