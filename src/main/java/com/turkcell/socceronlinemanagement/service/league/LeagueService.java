package com.turkcell.socceronlinemanagement.service.league;

import com.turkcell.socceronlinemanagement.service.league.LeagueRequest;
import com.turkcell.socceronlinemanagement.service.league.LeagueResponse;

import java.util.List;

public interface LeagueService {
    List<LeagueResponse> getAll();

    LeagueResponse getById(Integer id);

    LeagueResponse add(LeagueRequest request);

    LeagueResponse update(Integer id, LeagueRequest request);

    void delete(Integer id);
}
