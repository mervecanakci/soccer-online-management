package com.turkcell.socceronlinemanagement.service.administrator;

import java.util.List;

public interface AdministratorService {
    List<AdministratorResponse> getAll();

    AdministratorResponse getById(Integer id);

    AdministratorResponse add(AdministratorRequest request);

    AdministratorResponse update(Integer id, AdministratorRequest request);

    void delete(Integer id);
}

