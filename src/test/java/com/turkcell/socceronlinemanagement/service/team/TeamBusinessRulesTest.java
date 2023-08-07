package com.turkcell.socceronlinemanagement.service.team;

import com.turkcell.socceronlinemanagement.advice.exception.BusinessException;
import com.turkcell.socceronlinemanagement.repository.TeamRepository;
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

@ContextConfiguration(classes = {TeamBusinessRules.class})
@ExtendWith(SpringExtension.class)
class TeamBusinessRulesTest {
    @Autowired
    private TeamBusinessRules teamBusinessRules;

    @MockBean
    private TeamRepository teamRepository;

    /**
     * Method under test: {@link TeamBusinessRules#checkIfTeamExistsById(int)}
     */
    @Test
    void testCheckIfTeamExistsById() {
        when(teamRepository.existsById(Mockito.<Integer>any())).thenReturn(true);
        teamBusinessRules.checkIfTeamExistsById(1);
        verify(teamRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link TeamBusinessRules#checkIfTeamExistsById(int)}
     */
    @Test
    void testCheckIfTeamExistsById2() {
        when(teamRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        assertThrows(BusinessException.class, () -> teamBusinessRules.checkIfTeamExistsById(1));
        verify(teamRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link TeamBusinessRules#checkIfTeamExistsById(int)}
     */
    @Test
    void testCheckIfTeamExistsById3() {
        when(teamRepository.existsById(Mockito.<Integer>any())).thenThrow(new BusinessException("An error occurred"));
        assertThrows(BusinessException.class, () -> teamBusinessRules.checkIfTeamExistsById(1));
        verify(teamRepository).existsById(Mockito.<Integer>any());
    }
}

