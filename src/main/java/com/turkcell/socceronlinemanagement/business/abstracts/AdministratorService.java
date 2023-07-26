package com.turkcell.socceronlinemanagement.business.abstracts;

import com.turkcell.socceronlinemanagement.business.dto.requests.AdministratorRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.AdministratorResponse;

import java.util.List;

public interface AdministratorService {
    List<AdministratorResponse> getAll();

    AdministratorResponse getById(int id);

    AdministratorResponse add(AdministratorRequest request);

    AdministratorResponse update(int id, AdministratorRequest request);

    void delete(int id);
}

