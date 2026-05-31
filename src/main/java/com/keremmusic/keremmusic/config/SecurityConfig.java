package com.keremmusic.keremmusic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Şimdilik geliştirme aşamasında tarayıcıdan gelen POST/PUT isteklerinin engellenmemesi için CSRF'i kapatıyoruz
                .csrf(csrf -> csrf.disable())

                // Sayfa izinlerini ayarla
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // H2 veritabanına izin ver
                        .requestMatchers("/", "/index.html", "/songs.html", "/tables.html", "/css/**", "/js/**", "/img/**", "/vendor/**").permitAll() // Frontend dosyalarına izin ver
                        .anyRequest().permitAll() // Şimdilik geliştirme aşamasında diğer her şeye de izin ver
                )
                // H2 Console'un tarayıcıda (iframe içinde) kilitlenmesini engelle
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Şifreleme hatasını çözer
    }
}