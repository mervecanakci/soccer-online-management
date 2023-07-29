package com.turkcell.socceronlinemanagement.service.league;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LeagueRequest {

    @NotBlank
    private String country;

    @NotBlank
    private String leagueName;

}