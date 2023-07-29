package com.turkcell.socceronlinemanagement.service.transfer;

import com.turkcell.socceronlinemanagement.service.payment.PaymentRequest;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {


//    @Min(1)
//    private int teamId;

//    @NotNull
 //   private double teamValue;

    @Min(1)
    private Integer playerId;

    //@NotNull
  //  private double playerMarketValue;

    private LocalDateTime dateOfTransfer;

    private boolean isCompleted;

    private PaymentRequest paymentRequest;
}
