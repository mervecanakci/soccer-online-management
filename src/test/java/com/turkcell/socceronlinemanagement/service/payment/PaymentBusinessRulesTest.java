package com.turkcell.socceronlinemanagement.service.payment;

import com.turkcell.socceronlinemanagement.advice.exception.BusinessException;
import com.turkcell.socceronlinemanagement.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {PaymentBusinessRules.class})
@ExtendWith(SpringExtension.class)
class PaymentBusinessRulesTest {
    @Autowired
    private PaymentBusinessRules paymentBusinessRules;

    @MockBean
    private PaymentRepository paymentRepository;

    /**
     * Method under test: {@link PaymentBusinessRules#checkIfPaymentExists(int)}
     */
    @Test
    void testCheckIfPaymentExists() {
        when(paymentRepository.existsById(Mockito.<Integer>any())).thenReturn(true);
        paymentBusinessRules.checkIfPaymentExists(1);
        verify(paymentRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link PaymentBusinessRules#checkIfPaymentExists(int)}
     */
    @Test
    void testCheckIfPaymentExists2() {
        when(paymentRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        assertThrows(BusinessException.class, () -> paymentBusinessRules.checkIfPaymentExists(1));
        verify(paymentRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link PaymentBusinessRules#checkIfPaymentExists(int)}
     */
    @Test
    void testCheckIfPaymentExists3() {
        when(paymentRepository.existsById(Mockito.<Integer>any())).thenThrow(new BusinessException("An error occurred"));
        assertThrows(BusinessException.class, () -> paymentBusinessRules.checkIfPaymentExists(1));
        verify(paymentRepository).existsById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link PaymentBusinessRules#checkIfBalanceIsEnough(double, double)}
     */
    @Test
    void testCheckIfBalanceIsEnough() {
        paymentBusinessRules.checkIfBalanceIsEnough(10.0d, 10.0d);
    }

    /**
     * Method under test: {@link PaymentBusinessRules#checkIfBalanceIsEnough(double, double)}
     */
    @Test
    void testCheckIfBalanceIsEnough2() {
        assertThrows(BusinessException.class, () -> paymentBusinessRules.checkIfBalanceIsEnough(10.0d, 0.5d));
    }
}

