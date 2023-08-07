package com.turkcell.socceronlinemanagement.security.filter;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.turkcell.socceronlinemanagement.model.User;
import com.turkcell.socceronlinemanagement.model.enums.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = {JwtAuthenticationFilter.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
class JwtAuthenticationFilterTest {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    void testDoFilterInternal() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    }

    @Test
    @Disabled("Complete ")
    void testDoFilterInternal2() throws ServletException, IOException {

        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jwtAuthenticationFilter.doFilterInternal(null, response, filterChain);
    }

    @Test
    void testDoFilterInternal3() throws ServletException, IOException {
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        verify(request).getHeader(Mockito.<String>any());
        verify(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    }

    @Test
    @Disabled(" Complete ")
    void testDoFilterInternal4() throws ServletException, IOException, UsernameNotFoundException {

        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(new User());
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
    }

    @Test
    void testDoFilterInternal5() throws ServletException, IOException, UsernameNotFoundException {
        when(jwtService.isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(true);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");

        User user = new User();
        user.setRole(Role.USER);
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(user);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(request.getRemoteAddr()).thenReturn("42 Main St");
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        verify(jwtService).isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any());
        verify(jwtService).extractUsername(Mockito.<String>any());
        verify(userDetailsService).loadUserByUsername(Mockito.<String>any());
        verify(request).getSession(anyBoolean());
        verify(request).getRemoteAddr();
        verify(request).getHeader(Mockito.<String>any());
        verify(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    }

    @Test
    void testDoFilterInternal6() throws ServletException, IOException, UsernameNotFoundException {
        when(jwtService.isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(false);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");

        User user = new User();
        user.setRole(Role.USER);
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(user);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        verify(jwtService).isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any());
        verify(jwtService).extractUsername(Mockito.<String>any());
        verify(userDetailsService).loadUserByUsername(Mockito.<String>any());
        verify(request).getHeader(Mockito.<String>any());
        verify(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    }

    @Test
    void testDoFilterInternal7() throws ServletException, IOException {
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn(null);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        verify(jwtService).extractUsername(Mockito.<String>any());
        verify(request).getHeader(Mockito.<String>any());
        verify(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());
    }
}

