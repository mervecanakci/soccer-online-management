package com.turkcell.socceronlinemanagement.service.player;


import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerBusinessRules {
    private final PlayerRepository repository;

    public void checkIfPlayerExistsById(int id) {
        if (!repository.existsById(id))
            throw new BusinessException(Messages.Player.NOT_EXISTS);
    }


}
