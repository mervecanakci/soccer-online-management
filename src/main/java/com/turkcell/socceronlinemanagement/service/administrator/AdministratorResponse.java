package com.turkcell.socceronlinemanagement.service.administrator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorResponse {
    private Integer id;
    private String nickname;
    private String password;

    /********************************
     private int leagueId;
     private String leagueCountry;
     private LeagueName leagueName;

     private int playerId;
     private String playerName;
     private String playerSurname;
     private String playerCountry;
     private int playerAge;
     private double playerHeight;
     private double playerWeight;
     private double playerMarketValue;
     private Position position;

     private int teamId;
     private String teamName;
     private double teamValue;
     private double teamTotalBalance;

     private int transferId;
     private String transferOldTeam;
     private String transferNewTeam;
     private LocalDateTime transferDateOfTransfer;

     private int transferFeeId;
     private double transferFeeTotalBalance;

     private int userId;
     private String userEmail;
     private String userPassword;
     **/

}
