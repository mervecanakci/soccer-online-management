package com.turkcell.socceronlinemanagement.service.transfer;

import java.util.List;

public interface TransferService {
    List<TransferResponse> getAll();

    TransferResponse getById(Integer id);

    TransferResponse returnPlayerFromTransfer(Integer playerId);

    TransferResponse add(TransferRequest request);

    TransferResponse update(Integer id, TransferRequest request);

    void delete(Integer id);
}
