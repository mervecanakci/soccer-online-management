package com.turkcell.socceronlinemanagement.security.config;

import com.turkcell.socceronlinemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor // final değişkenler için constructor oluşturur
public class ApplicationConfig {
    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username) // "username" ile kullanıcı bulunur /email/
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {  // "authenticationProvider" bean'i oluşturulur
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();  // authProvider ı DaoAuthenticationProvider sınıfından  türetir
        authProvider.setUserDetailsService(userDetailsService()); // userDetailsService bean'i authProvider bean'ine set edilir
        authProvider.setPasswordEncoder(passwordEncoder()); // passwordEncoder authProvider a set edilir
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { // kimlik doğrulaması
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } // BCryptPasswordEncoder: şifreleme algoritması olarak BCrypt kullanılır

}
