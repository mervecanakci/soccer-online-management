package com.turkcell.socceronlinemanagement.service.transfer;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

 //   private int teamId;
    //    @NotNull
 @JsonIgnore
    private double teamValue;

    private int playerId;
    @JsonIgnore
    private String playerName;
    @JsonIgnore
    private String teamName;

    @JsonIgnore
    private double playerMarketValue;

    private double price;




    }
    // private PaymentRequest paymentRequest;

    //@NotNull
    //  private double playerMarketValue;
    //  private LocalDateTime dateOfTransfer;
    //  private boolean isCompleted;


//eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6IkFETUlOIiwic3ViIjoibWVydmUzIiwiaWF0IjoxNjkxMDQwMTcxLCJleHAiOjE2OTEwNDE2MTF9.FCqgbc6_BKKUu1Gf1q4A2iAcIy64W9yhe9RlRzPlb70