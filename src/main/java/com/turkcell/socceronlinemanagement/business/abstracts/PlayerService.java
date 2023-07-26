package com.turkcell.socceronlinemanagement.business.abstracts;

import com.turkcell.socceronlinemanagement.business.dto.requests.PlayerRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.PlayerResponse;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;

import java.util.List;

public interface PlayerService {
    List<PlayerResponse> getAll(boolean includeTransfer);

    PlayerResponse getById(int id);

    PlayerResponse add(PlayerRequest request);

    PlayerResponse update(int id, PlayerRequest request);

    void delete(int id);

    void changeTransferState(int playerIdd, TransferState transferState);


}
