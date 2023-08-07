package com.turkcell.socceronlinemanagement.security.filter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.turkcell.socceronlinemanagement.model.User;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtService.class})
@ExtendWith(SpringExtension.class)
class JwtServiceTest {
    @Autowired
    private JwtService jwtService;

    @Test
    @Disabled("TODO: Complete this test")
    void testExtractUsername() {

        jwtService.extractUsername("ABC123");
    }

    @Test
    @Disabled("Complete ")
    void testExtractClaim() {

        String token = "";
        Function<Claims, Object> claimsResolver = null;

        Object actualExtractClaimResult = this.jwtService.extractClaim(token, claimsResolver);

    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken() {

        HashMap<String, Object> extractClaims = new HashMap<>();
        jwtService.generateToken(extractClaims, new User());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken2() {

        HashMap<String, Object> extractClaims = new HashMap<>();
        extractClaims.put("foo", "42");
        jwtService.generateToken(extractClaims, new User());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken3() {

        jwtService.generateToken(new HashMap<>(), null);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken4() {

        HashMap<String, Object> extractClaims = new HashMap<>();
        User userDetails = mock(User.class);
        when(userDetails.getUsername()).thenReturn("janedoe");
        jwtService.generateToken(extractClaims, userDetails);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken5() {

        jwtService.generateToken(new User());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken6() {

        jwtService.generateToken(null);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken7() {

        User userDetails = mock(User.class);
        when(userDetails.getUsername()).thenReturn("janedoe");
        jwtService.generateToken(userDetails);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testIsTokenValid() {

        jwtService.isTokenValid("ABC123", new User());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testIsTokenValid2() {

        jwtService.isTokenValid("ABC123", mock(User.class));
    }
}

