package com.turkcell.socceronlinemanagement.common.dto;


import com.turkcell.socceronlinemanagement.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransferPaymentRequest {
  //  private int userId;
    private int teamId;
    private int playerId;
//    private String newTeamName;
//    private String oldTeamName;
//    private String playerFirstName;
//    private String playerLastName;
    private double teamValue;
    private double playerMarketValue;

}//todo: böyle değil düzenle payment manager da kullandıklarına göre
