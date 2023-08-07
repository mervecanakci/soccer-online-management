package com.turkcell.socceronlinemanagement.service.player;

import com.turkcell.socceronlinemanagement.advice.exception.BusinessException;
import com.turkcell.socceronlinemanagement.repository.PlayerRepository;
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

@ContextConfiguration(classes = {PlayerBusinessRules.class})
@ExtendWith(SpringExtension.class)
class PlayerBusinessRulesTest {
    @Autowired
    private PlayerBusinessRules playerBusinessRules;

    @MockBean
    private PlayerRepository playerRepository;

    @Test
    void testCheckIfPlayerExistsById() {
        when(playerRepository.existsById(Mockito.<Integer>any())).thenReturn(true);
        playerBusinessRules.checkIfPlayerExistsById(1);
        verify(playerRepository).existsById(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfPlayerExistsById2() {
        when(playerRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        assertThrows(BusinessException.class, () -> playerBusinessRules.checkIfPlayerExistsById(1));
        verify(playerRepository).existsById(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfPlayerExistsById3() {
        when(playerRepository.existsById(Mockito.<Integer>any())).thenThrow(new BusinessException("An error occurred"));
        assertThrows(BusinessException.class, () -> playerBusinessRules.checkIfPlayerExistsById(1));
        verify(playerRepository).existsById(Mockito.<Integer>any());
    }
}

