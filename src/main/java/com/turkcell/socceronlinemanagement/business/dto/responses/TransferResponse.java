package com.turkcell.socceronlinemanagement.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private int id;
    private int leagueId;
    private String leagueName;
    private int teamId;
    private int oldTeamId;
    private int newTeamId;
    private String teamName;
    private double teamValue;
    private String playerId;
    private String playerName;
    private String playerCountry;
    private double playerMarketValue;
    private LocalDateTime dateOfTransfer;
    private boolean isCompleted;

}
