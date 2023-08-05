package com.turkcell.socceronlinemanagement.adapters;



import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.service.payment.PosService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService {
    @Override
    public void pay() { //ödeme işlemini simüle ediyor
        boolean isPaymentSuccessful = new Random().nextBoolean(); //rastgele bir boolean değer döndürüyor
        if (!isPaymentSuccessful) throw new BusinessException(Messages.Payment.FAILED); //ödeme başarısızsa hata fırlatıyor
    }
}
