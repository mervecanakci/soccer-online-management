package com.turkcell.socceronlinemanagement.service.payment;


import com.turkcell.socceronlinemanagement.advice.exception.BusinessException;
import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentBusinessRules {
    private final PaymentRepository repository;

    public void checkIfPaymentExists(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Payment.NOT_FOUND);
        }
    }

    public void checkIfBalanceIsEnough(double playerMarketValue, double teamValue) {
        if (teamValue < playerMarketValue) {
            throw new BusinessException(Messages.Payment.NOT_ENOUGHT_MONEY);
        }
    }

}
