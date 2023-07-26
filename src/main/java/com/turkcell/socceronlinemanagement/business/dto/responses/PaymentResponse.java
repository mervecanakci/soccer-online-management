package com.turkcell.socceronlinemanagement.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private int id;
    private int teamId;
    private String teamName;
    private int playerId;
    private String playerFirstName;
    private String playerLastName;
    private String newTeamName;
    private String oldTeamName;
    private double balance;
}
