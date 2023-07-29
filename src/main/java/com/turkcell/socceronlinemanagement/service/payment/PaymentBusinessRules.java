package com.turkcell.socceronlinemanagement.service.payment;


import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.common.dto.CreateTransferPaymentRequest;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentExists(Integer id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Payment.NOT_FOUND);
        }
    }

    public void checkIfBalanceIsEnough(BigDecimal playerMarketValue, BigDecimal balance) {
        int comparisonResult = playerMarketValue.compareTo(balance);

        if (comparisonResult > 0) {
            throw new BusinessException(Messages.Payment.NOT_ENOUGHT_MONEY);
        }
    }
//todo hata veriyordu yorumda *** düzeldi test etmedin ama

    public void checkIfPaymentIsValid(CreateTransferPaymentRequest request) { //Ödemenin Geçerli olup olmadığını kontrol ediyoruz.
        if (!repository.existsByUserIdAndTeamIdAndPlayerId(
                request.getUserId(),
                request.getTeamId(),
                request.getPlayerId()
        ))
        {            throw new BusinessException(Messages.Payment.NOT_A_VALID_PAYMENT);
        }

    }
}
