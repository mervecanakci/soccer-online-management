package com.turkcell.socceronlinemanagement.service.user;

import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.advice.exception.BusinessException;
import com.turkcell.socceronlinemanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBusinessRules {
    private final UserRepository repository;

    public void checkIfUserExistsById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.User.NOT_EXISTS);
        }
    }
    public void checkIfUserExistsByEmail(String email) {
        if (repository.existsByEmailIgnoreCase(email)) {
            throw new BusinessException(Messages.User.EXISTS);
        }
    }

}
