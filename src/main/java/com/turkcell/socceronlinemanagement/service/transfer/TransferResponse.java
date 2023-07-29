package com.turkcell.socceronlinemanagement.service.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private Integer id;
    private Integer leagueId;
    private String leagueName;
    private Integer teamId;
    private Integer oldTeamId;
    private Integer newTeamId;
    private String teamName;
    private BigDecimal teamValue;
    private String playerId;
    private String playerName;
    private String playerCountry;
    private BigDecimal playerMarketValue;
    private LocalDateTime dateOfTransfer;
    private boolean  isCompleted;

}
