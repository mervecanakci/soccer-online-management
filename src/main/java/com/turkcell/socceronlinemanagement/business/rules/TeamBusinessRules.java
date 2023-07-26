package com.turkcell.socceronlinemanagement.business.rules;

import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeamBusinessRules {

    private final TeamRepository repository;

    public void checkIfTeamExistsById(int id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.Team.NOT_EXISTS);
    }
}