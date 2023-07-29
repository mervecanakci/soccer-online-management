package com.turkcell.socceronlinemanagement.service.administrator;


import com.turkcell.socceronlinemanagement.model.Administrator;
import com.turkcell.socceronlinemanagement.repository.AdministratorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministratorManager implements AdministratorService {
    private final AdministratorRepository repository;
    private final ModelMapper mapper;
    private final AdministratorBusinessRules rules;

    @Override
    public List<AdministratorResponse> getAll() {
        List<Administrator> administrators = repository.findAll();
        List<AdministratorResponse> response = administrators
                .stream()
                .map(administrator -> mapper.map(administrator, AdministratorResponse.class))
                .toList();

        return response;
    }

    @Override
    public AdministratorResponse getById(Integer id) {
        rules.checkIfAdministratorExistsById(id);
        Administrator administrator = repository.findById(id).orElseThrow();
        AdministratorResponse response = mapper.map(administrator, AdministratorResponse.class);

        return response;
    }

    @Override
    public AdministratorResponse add(AdministratorRequest request) {
        Administrator administrator = mapper.map(request, Administrator.class);
        administrator.setId(0);
        repository.save(administrator);
        AdministratorResponse response = mapper.map(administrator, AdministratorResponse.class);

        return response;
    }

    @Override
    public AdministratorResponse update(Integer id, AdministratorRequest request) {
        rules.checkIfAdministratorExistsById(id);
        Administrator administrator = mapper.map(request, Administrator.class);
        administrator.setId(id);
        repository.save(administrator);
        AdministratorResponse response = mapper.map(administrator, AdministratorResponse.class);

        return response;
    }

    @Override
    public void delete(Integer id) {
        rules.checkIfAdministratorExistsById(id);
        repository.deleteById(id);
    }
}
