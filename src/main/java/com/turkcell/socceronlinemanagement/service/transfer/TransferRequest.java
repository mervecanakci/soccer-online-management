package com.turkcell.socceronlinemanagement.service.transfer;

import com.turkcell.socceronlinemanagement.service.payment.PaymentRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.net.ssl.SSLSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

    private int teamId;
    //    @NotNull
//    private double teamValue;

    private int playerId;

    private double playerMarketValue;

    private double price;
    private double teamValue;


    }
    // private PaymentRequest paymentRequest;

    //@NotNull
    //  private double playerMarketValue;
    //  private LocalDateTime dateOfTransfer;
    //  private boolean isCompleted;


//eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IkFETUlOIiwic3ViIjoibWVydmUzIiwiaWF0IjoxNjkxMDQwMTcxLCJleHAiOjE2OTEwNDE2MTF9.FCqgbc6_BKKUu1Gf1q4A2iAcIy64W9yhe9RlRzPlb70