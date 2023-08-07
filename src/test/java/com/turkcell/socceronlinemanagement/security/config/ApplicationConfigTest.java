package com.turkcell.socceronlinemanagement.security.config;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.turkcell.socceronlinemanagement.model.User;
import com.turkcell.socceronlinemanagement.model.enums.Role;
import com.turkcell.socceronlinemanagement.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ApplicationConfig.class, AuthenticationConfiguration.class})
@ExtendWith(SpringExtension.class)
class ApplicationConfigTest {
    @Autowired
    private ApplicationConfig applicationConfig;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testUserDetailsService() throws UsernameNotFoundException {

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTeams(new ArrayList<>());
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(user));
        UserDetails actualLoadUserByUsernameResult = (new ApplicationConfig(userRepository)).userDetailsService()
                .loadUserByUsername("janedoe");
        verify(userRepository).findByEmail(Mockito.<String>any());
        assertSame(user, actualLoadUserByUsernameResult);
    }

    @Test
    void testUserDetailsService2() throws UsernameNotFoundException {

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,
                () -> (new ApplicationConfig(userRepository)).userDetailsService().loadUserByUsername("janedoe"));
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    @Test
    void testUserDetailsService3() throws UsernameNotFoundException {

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any()))
                .thenThrow(new UsernameNotFoundException("User not found!"));
        assertThrows(UsernameNotFoundException.class,
                () -> (new ApplicationConfig(userRepository)).userDetailsService().loadUserByUsername("janedoe"));
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    @Test
    void testAuthenticationProvider() {
        assertTrue(applicationConfig.authenticationProvider() instanceof DaoAuthenticationProvider);
    }

    @Test
    void testAuthenticationManager() throws Exception {
        assertTrue(applicationConfig.authenticationManager(new AuthenticationConfiguration()) instanceof ProviderManager);
    }

    @Test
    void testPasswordEncoder() {
        assertTrue(applicationConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }
}

