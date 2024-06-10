package com.jpcenz.prueba.infraestructure.security.config;

import com.jpcenz.prueba.infraestructure.security.JwtFilter;
import com.jpcenz.prueba.infraestructure.security.repository.SecurityContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
@RequiredArgsConstructor
@Configuration
public class MainSecurity {

    private final SecurityContextRepository securityContextRepository;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http , JwtFilter jwtFilter) {
        return http

                //.csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
                    .pathMatchers(/*"/api/auth/**",*/ "/api/auth/**").permitAll()
                    .anyExchange().authenticated()
            )
            .addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
            .securityContextRepository(securityContextRepository)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .logout(ServerHttpSecurity.LogoutSpec::disable)
                .csrf(csrf -> csrf.disable())
                .build();

    }




}