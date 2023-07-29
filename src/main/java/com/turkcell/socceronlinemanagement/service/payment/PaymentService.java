package com.turkcell.socceronlinemanagement.service.payment;


import com.turkcell.socceronlinemanagement.common.dto.CreateTransferPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<PaymentResponse> getAll();

    PaymentResponse getById(Integer id);

    PaymentResponse add(PaymentRequest request);

    PaymentResponse update(Integer id, PaymentRequest request);

    void delete(Integer id);

    void processTransferPayment(CreateTransferPaymentRequest request);

}
