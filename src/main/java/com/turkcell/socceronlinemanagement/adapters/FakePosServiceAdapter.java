package com.turkcell.socceronlinemanagement.adapters;

import com.turkcell.socceronlinemanagement.service.payment.PosService;
import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if (!isPaymentSuccessful) throw new BusinessException(Messages.Payment.FAILED);
    }
}
