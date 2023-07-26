package com.turkcell.socceronlinemanagement.business.abstracts;

import com.turkcell.socceronlinemanagement.business.dto.requests.LeagueRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.LeagueResponse;

import java.util.List;

public interface LeagueService {
    List<LeagueResponse> getAll();

    LeagueResponse getById(int id);

    LeagueResponse add(LeagueRequest request);

    LeagueResponse update(int id, LeagueRequest request);

    void delete(int id);
}
