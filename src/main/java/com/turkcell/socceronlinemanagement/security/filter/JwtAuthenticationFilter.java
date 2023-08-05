package com.turkcell.socceronlinemanagement.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter { //OncePerRequestFilter, her istek için yalnızca bir kez çağrılan bir filtre sağlar
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(  //doFilterInternal, filtreleme mantığını uygulamak için kullanılır
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

 //       Map<String, String> map = new HashMap<String, String>();

//        Enumeration headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String key = (String) headerNames.nextElement();
//            System.out.println(key);
//            String value = request.getHeader(key);
//            System.out.println(value);
//            map.put(key, value);
//        }

        final String authHeader = request.getHeader("Authorization"); //Authorization header'ını alıyoruz
        final String jwt;
        final String username;
        if (authHeader == null || !Optional.of(authHeader).orElse("").trim().contains("Bearer ")) { // .trim() : boşlukları siler
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7); //Bearer'ı silip sadece tokeni alıyoruz
        username = jwtService.extractUsername(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // kullanıcı daha önce kimlik doğrulaması yapmamışsa
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); //loadUserByUsername methodu ile kullanıcı bilgilerini alıyoruz
            var authorities = new HashSet<GrantedAuthority>(userDetails.getAuthorities().size()); //HashSet: aynı elemandan birden fazla olmaz
            for (var role : userDetails.getAuthorities()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString())); //Kullanıcının rollerini alıyoruz ve ROLE_ ile başlatıyoruz
            }
            if (jwtService.isTokenValid(jwt, userDetails)) { //Token geçerli mi değil mi
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( //kullanıcı adı ve şifre ile kimlik doğrulaması yapar
                        userDetails, null, authorities
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request) // kullanıcının kimlik doğrulaması için kullanılan bilgileri tutar
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}
