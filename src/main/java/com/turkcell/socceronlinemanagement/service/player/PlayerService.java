package com.turkcell.socceronlinemanagement.service.player;

import com.turkcell.socceronlinemanagement.model.enums.TransferState;

import java.math.BigDecimal;
import java.util.List;

public interface PlayerService {
    List<PlayerResponse> getAll(boolean includeTransfer);

    PlayerResponse getById(Integer id);

    List<PlayerResponse> add(PlayerRequest request);

    // List<PlayerResponse>  add(PlayerRequest request, Integer marketValue);
    PlayerResponse update(Integer id, PlayerRequest request);

    void delete(Integer id);

    void changeTransferState(Integer playerId, TransferState transferState);


}
