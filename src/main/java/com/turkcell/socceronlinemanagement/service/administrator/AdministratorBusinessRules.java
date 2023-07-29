package com.turkcell.socceronlinemanagement.service.administrator;

import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.repository.AdministratorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministratorBusinessRules {

    private final AdministratorRepository repository;

    public void checkIfAdministratorExistsById(Integer id) {
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
