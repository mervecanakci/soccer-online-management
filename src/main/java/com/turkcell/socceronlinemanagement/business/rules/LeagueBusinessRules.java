package com.turkcell.socceronlinemanagement.business.rules;

import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.repository.LeagueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeagueBusinessRules {
    private final LeagueRepository repository;

    public void checkIfLeagueExistsById(int id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.League.NOT_EXISTS);
    }
/*
    public void checkIfLeagueExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new BusinessException(Messages.League.Exists);
        }
    } */

}