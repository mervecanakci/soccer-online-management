package com.turkcell.socceronlinemanagement.service.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private Integer id;
    private Integer teamId;
   // private Integer oldTeamId; //todo: eski takımı da tutmak lazım
   // private Integer newTeamId;
    private String teamName;
    private double teamValue;
  //  private String playerId;
    private String playerName;
    private String playerCountry;
    private double playerMarketValue;
    private double  priceRequest;
    private LocalDateTime dateOfTransfer;
    private boolean  isCompleted;

}
