package com.turkcell.socceronlinemanagement.service.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private int id;
    private String teamName;
    private double teamValue;
    private int playerId;
    private String playerName;
    private double  price;
    private LocalDateTime dateOfTransfer;

}
