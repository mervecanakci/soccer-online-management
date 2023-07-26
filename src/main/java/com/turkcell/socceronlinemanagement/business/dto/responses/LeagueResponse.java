package com.turkcell.socceronlinemanagement.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LeagueResponse {

    private int id;
    private String country;
    private String leagueName;

}