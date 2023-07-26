package com.turkcell.socceronlinemanagement.business.abstracts;

import com.turkcell.socceronlinemanagement.business.dto.requests.TeamRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.TeamResponse;

import java.util.List;

public interface TeamService {
    List<TeamResponse> getAll();

    TeamResponse getById(int id);

    TeamResponse add(TeamRequest request);

    TeamResponse update(int id, TeamRequest request);

    void delete(int id);
}
