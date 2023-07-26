package com.turkcell.socceronlinemanagement.business.rules;

import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.repository.AdministratorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdministratorBusinessRules {

    private final AdministratorRepository repository;

    public void checkIfAdministratorExistsById(int id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.Administrator.NOT_EXISTS);
    }
/**
 public void checkIfAdministratorExistsByName(String name) {
 if (repository.existsByNameIgnoreCase(name)) {
 throw new BusinessException(Messages.Administrator.Exists);
 }
 }

 */
}
