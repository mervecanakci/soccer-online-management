package com.turkcell.socceronlinemanagement.business.dto.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

  //  @NotBlank
    @Min(1)
    private int teamId;

//    @NotNull
 //   private double teamValue;

    @NotNull
    @Min(1)
    private int playerId;

    //@NotNull
  //  private double playerMarketValue;

    private LocalDateTime dateOfTransfer;

    private boolean isCompleted;

    private PaymentRequest paymentRequest;
}
