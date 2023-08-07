package com.turkcell.socceronlinemanagement.security.config;

import com.turkcell.socceronlinemanagement.security.filter.JwtAuthenticationFilter;

import java.util.HashMap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SecurityConfig.class})
@ExtendWith(SpringExtension.class)
class SecurityConfigTest {
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private SecurityConfig securityConfig;

    @Test
    @Disabled("Complete")
    void testSecurityFilterChain() throws Exception {

        AuthenticationManagerBuilder authenticationBuilder = new AuthenticationManagerBuilder(null);
        securityConfig.securityFilterChain(new HttpSecurity(null, authenticationBuilder, new HashMap<>()));
    }
}

