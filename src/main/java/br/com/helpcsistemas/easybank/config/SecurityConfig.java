package br.com.helpcsistemas.easybank.config;

import br.com.helpcsistemas.easybank.constants.Constants;
import br.com.helpcsistemas.easybank.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
public class SecurityConfig {




    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList(Constants.ALLOWED_ORIGIN_URL));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(List.of(Constants.HEADER_AUTHORIZATION));
                        config.setMaxAge(3600L);
                        return config;
                    }
                })).csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers(Constants.ENDPOINT_CONTACT, Constants.ENDPOINT_REGISTER)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests)->requests
                        .requestMatchers(Constants.ENDPOINT_MY_ACCOUNT).hasRole(Constants.ROLE_USER)
                        .requestMatchers(Constants.ENDPOINT_MY_BALANCE).hasAnyRole(Constants.ROLE_USER, Constants.ROLE_ADMIN)
                        .requestMatchers(Constants.ENDPOINT_MY_LOANS).hasRole(Constants.ROLE_USER)
                        .requestMatchers(Constants.ENDPOINT_MY_CARDS).hasRole(Constants.ROLE_USER)
                        .requestMatchers(Constants.ENDPOINT_USER).authenticated()
                        .requestMatchers(Constants.ENDPOINT_NOTICES, Constants.ENDPOINT_CONTACT, Constants.ENDPOINT_REGISTER).permitAll())
                .oauth2ResourceServer(oauth2ResourceServerCustomizer ->
                        oauth2ResourceServerCustomizer.jwt(jwtCustomizer -> jwtCustomizer.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(Constants.B_CRYPT_VERSION, Constants.B_CRIPT_STRENGTH);
    }

}
