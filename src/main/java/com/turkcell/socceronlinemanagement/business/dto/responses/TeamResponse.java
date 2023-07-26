package com.turkcell.socceronlinemanagement.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamResponse {
    private int id;
    private String teamName;
    private double teamValue;
    private double totalBalance; //todo
    private String teamCountry;
    private int leagueId;
    private String leagueName;
    private String userEmail;
//    private String userLastName;
//    private String userFirstName;
}