package com.turkcell.socceronlinemanagement.service.transfer;

import lombok.*;

@Data
@RequiredArgsConstructor
public class TransferPlayerRequest {
    private Integer teamId;
    private Integer playerId;
    private double price;
}
