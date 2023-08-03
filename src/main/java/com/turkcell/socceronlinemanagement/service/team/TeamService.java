package com.turkcell.socceronlinemanagement.service.team;

import com.turkcell.socceronlinemanagement.service.transfer.TransferPlayerRequest;

import java.util.List;

public interface TeamService {
    List<TeamResponse> getAll();

   TeamResponse getById(int id);

    TeamResponse add(TeamRequest request);

    TeamResponse update(int id, TeamRequest request);

    void delete(int id);

    TeamResponse addTransferPlayer(TransferPlayerRequest request);
}
