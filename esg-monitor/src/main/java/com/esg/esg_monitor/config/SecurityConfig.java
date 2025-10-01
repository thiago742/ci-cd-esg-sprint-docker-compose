package com.esg.esg_monitor.config;

import com.esg.esg_monitor.security.JwtAuthenticationFilter;
import com.esg.esg_monitor.security.UsuarioDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
//@RequiredArgsConstructor
public class SecurityConfig {

    private final UsuarioDetailsService usuarioDetailsService;
    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(UsuarioDetailsService usuarioDetailsService, JwtAuthenticationFilter jwtAuthFilter) {
        this.usuarioDetailsService = usuarioDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()

                        // o metodo de get e post sao disponiveis para user e admin, mas outros metodos como put ou delete sao apenas para o admin,
                        // para simplificar coloquei que qualquer endpoint que passe do endpoint base precise do admin,
                        // ja que para usar o verbo put ou delete é necessario o endpoint base + id

                        .requestMatchers("/empresas", "/equipamentos", "/manutencoes", "/relatorios").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/empresas/**", "/equipamentos/**", "/manutencoes/**", "/relatorios/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .userDetailsService(usuarioDetailsService)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
