package com.turkcell.socceronlinemanagement.service.team;

import com.turkcell.socceronlinemanagement.service.transfer.TransferPlayerRequest;

import java.util.List;

public interface TeamService {
    List<TeamResponse> getAll();

    TeamResponse getById(Integer id);

    TeamResponse add(TeamRequest request);

    TeamResponse update(Integer id, TeamRequest request);

    void delete(Integer id);

    TeamResponse addTransferPlayer(TransferPlayerRequest request);
}
