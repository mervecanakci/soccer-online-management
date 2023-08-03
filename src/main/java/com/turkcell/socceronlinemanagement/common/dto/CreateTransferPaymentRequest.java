package com.turkcell.socceronlinemanagement.common.dto;


import com.turkcell.socceronlinemanagement.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransferPaymentRequest {
    private int teamId;
    private int playerId;
    private double teamValue;
    private double playerMarketValue;

}/
