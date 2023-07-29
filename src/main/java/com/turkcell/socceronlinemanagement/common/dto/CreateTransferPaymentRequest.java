package com.turkcell.socceronlinemanagement.common.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransferPaymentRequest {
    private Integer userId;
    private Integer teamId;
    private Integer playerId;
    private String newTeamName;
    private String oldTeamName;
    private String playerFirstName;
    private String playerLastName;
    private BigDecimal balance;
    private BigDecimal playerMarketValue;
    private String teamCountry;
}//todo: böyle değil düzenle payment manager da kullandıklarına göre
