package com.turkcell.socceronlinemanagement.security.config;


import com.turkcell.socceronlinemanagement.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf() //csrf: cross site request forgery istek dolandırılıcılığı gibi isteklerin önüne geçmek için kullanılır
                .disable()  //csrf disable ediyoruz çünkü şu anda bu isteklerin önüne geçmek için bir önlem almamıza gerek yok
                .authorizeHttpRequests() //http isteklerini authorize ediyoruz
                .requestMatchers("/api/users/**", "/spring-actuator/**","/swagger-ui/**")   //bu isteklerin hepsine izin veriyor
                .permitAll()
                //.hasAnyAuthority("ROLE_ADMIN")  //hasAnyAuthority: verilen yetkilere sahip olan kullanıcıları arar
//                .hasRole(Role.ADMIN.name()) // hasRole:  "ROLE_" ön eki ile verilen rolü arar
                .anyRequest() //herhangi bir istek
                .authenticated() // kimlik doğrulaması yapılmış olan kullanıcıları arar
                .and()
                .sessionManagement()    // session yönetimi yani oturum yönetimi yapılır
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // oturum oluşturma politikası belirlenir yani oturum oluşturulmaz nedeni ise token ile kimlik doğrulaması yapılacağı için
                .and()
                .authenticationProvider(authenticationProvider) //  kimlik doğrulaması sağlayan bean
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // jwtAuthFilter: jwt ile kimlik doğrulaması yapılır, addFilterBefore: filtreleme işlemi yapılır

        return http.build(); // http build edilir
    }
}
//            .hasAnyAuthority(String.valueOf(Role.ADMIN))
//                .hasAnyAuthority("ADMIN")
