package com.turkcell.socceronlinemanagement.service.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Integer id;
    private Integer teamId;
    private String teamName;
    private Integer playerId;
    private String playerFirstName;
    private String playerLastName;
    private String newTeamName;
    private String oldTeamName;
    private BigDecimal balance;
}
