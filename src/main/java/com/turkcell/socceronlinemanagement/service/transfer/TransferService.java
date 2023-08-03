package com.turkcell.socceronlinemanagement.service.transfer;

import java.util.List;

public interface TransferService {
    List<TransferResponse> getAll();

    TransferResponse getById(int id);

    TransferResponse returnPlayerFromTransfer(int playerId);

    TransferResponse add(TransferRequest request);

    TransferResponse update(int id, TransferRequest request);

    void delete(int id);
}
