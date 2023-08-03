package com.turkcell.socceronlinemanagement.service.transfer;

import lombok.*;

@Data
@RequiredArgsConstructor
public class TransferPlayerRequest {
    private int teamId;
    private int playerId;
    private double price;
}
