package com.turkcell.socceronlinemanagement.service.user;

import com.turkcell.socceronlinemanagement.advice.exception.BusinessException;
import com.turkcell.socceronlinemanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserBusinessRules.class})
@ExtendWith(SpringExtension.class)
class UserBusinessRulesTest {
    @Autowired
    private UserBusinessRules userBusinessRules;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testCheckIfUserExistsById() {
        when(userRepository.existsById(Mockito.<Integer>any())).thenReturn(true);
        userBusinessRules.checkIfUserExistsById(1);
        verify(userRepository).existsById(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfUserExistsById2() {
        when(userRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        assertThrows(BusinessException.class, () -> userBusinessRules.checkIfUserExistsById(1));
        verify(userRepository).existsById(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfUserExistsById3() {
        when(userRepository.existsById(Mockito.<Integer>any())).thenThrow(new BusinessException("An error occurred"));
        assertThrows(BusinessException.class, () -> userBusinessRules.checkIfUserExistsById(1));
        verify(userRepository).existsById(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfUserExistsByEmail() {
        when(userRepository.existsByEmailIgnoreCase(Mockito.<String>any())).thenReturn(true);
        assertThrows(BusinessException.class, () -> userBusinessRules.checkIfUserExistsByEmail("merve@gmail.com"));
        verify(userRepository).existsByEmailIgnoreCase(Mockito.<String>any());
    }

    @Test
    void testCheckIfUserExistsByEmail2() {
        when(userRepository.existsByEmailIgnoreCase(Mockito.<String>any())).thenReturn(false);
        userBusinessRules.checkIfUserExistsByEmail("merve@gmail.com");
        verify(userRepository).existsByEmailIgnoreCase(Mockito.<String>any());
    }

    @Test
    void testCheckIfUserExistsByEmail3() {
        when(userRepository.existsByEmailIgnoreCase(Mockito.<String>any()))
                .thenThrow(new BusinessException("An error occurred"));
        assertThrows(BusinessException.class, () -> userBusinessRules.checkIfUserExistsByEmail("merve@gmail.com"));
        verify(userRepository).existsByEmailIgnoreCase(Mockito.<String>any());

    }
}

