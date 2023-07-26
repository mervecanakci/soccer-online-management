package com.turkcell.socceronlinemanagement.business.rules;


import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.common.dto.CreateTransferPaymentRequest;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Payment.NOT_FOUND);
        }
    }

    public void checkIfBalanceIsEnough(double playerMarketValue, double balance) {
        if (balance < playerMarketValue) {
            throw new BusinessException(Messages.Payment.NOT_ENOUGHT_MONEY);
        }
    }
//todo hata veriyordu yorumda *** düzeldi test etmedin ama

    public void checkIfPaymentIsValid(CreateTransferPaymentRequest request) { //Ödemenin Geçerli olup olmadığını kontrol ediyoruz.
        if (!repository.existsByUserIdAndTeamIdAndPlayerId(
                request.getUserId(),
                request.getTeamId(),
                request.getPlayerId()
        )) {
            throw new BusinessException(Messages.Payment.NOT_A_VALID_PAYMENT);
        }
    }
}
