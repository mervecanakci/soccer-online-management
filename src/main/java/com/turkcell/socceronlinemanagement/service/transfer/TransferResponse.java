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
    private int teamId;
   // private Integer oldTeamId; //todo: eski takımı da tutmak lazım
   // private Integer newTeamId;
    private String teamName;
    private double teamValue;
    private String playerId;
    private String playerName;
    private String playerCountry;
    private double playerMarketValue;
    private double  price;
    private LocalDateTime dateOfTransfer;
    private boolean  isCompleted;


}
