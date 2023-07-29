package com.turkcell.socceronlinemanagement.service.team;

import com.turkcell.socceronlinemanagement.service.player.PlayerResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {
    private Integer id;
    private String teamName;
    private BigDecimal teamValue;
  //  private BigDecimal totalBalance; //todo
    private String teamCountry;

    private List<PlayerResponse> players;
   // private Integer leagueId;
  //  private String leagueName;
   // private String userEmail;
//    private String userLastName;
//    private String userFirstName;

}