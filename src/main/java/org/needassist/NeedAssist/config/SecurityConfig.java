package org.needassist.NeedAssist.config;

import org.needassist.NeedAssist.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .formLogin(Customizer.withDefaults())
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
