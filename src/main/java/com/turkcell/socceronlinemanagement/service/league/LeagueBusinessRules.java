package com.turkcell.socceronlinemanagement.service.league;

import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.repository.LeagueRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeagueBusinessRules {
    private final LeagueRepository repository;

    public void checkIfLeagueExistsById(Integer id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.League.NOT_EXISTS);
    }
/*
    public void checkIfLeagueExistsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new BusinessException(Messages.League.Exists);
        }
    } */

}