package com.turkcell.socceronlinemanagement.service.transfer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransferPlayerRequest {
    private int teamId;
    private int playerId;
    private double price;
    @JsonIgnore
    private double playerMarketValue;
}
