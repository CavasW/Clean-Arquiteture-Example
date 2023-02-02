package backend.backend.config.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import backend.backend.application.common.interfaces.IJwtGenerator;
import backend.backend.config.providers.JwtConfiguration;
import backend.backend.infrastructure.providers.Authentication.JwtGenerator;
import backend.backend.presentation.middlewares.AuthenticationMiddleware;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final JwtConfiguration jwtConfiguration;

    // Generates Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Set's up JWT Generator
    @Bean
    public IJwtGenerator jwtGenerator() {
        return new JwtGenerator(jwtConfiguration);
    }

    // Set'up the Authentication Provider
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return (AuthenticationManager) configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .httpBasic().disable()
            .csrf().disable()
            .authenticationProvider(authProvider())
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .addFilterBefore(
                new AuthenticationMiddleware(
                    userDetailsService, 
                    jwtGenerator()
                ), 
                UsernamePasswordAuthenticationFilter.class
            )
            ;

        return http.build();

    }

    // Set'up the Authentication Provider
    private AuthenticationProvider authProvider() {
        
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;

    }

}
