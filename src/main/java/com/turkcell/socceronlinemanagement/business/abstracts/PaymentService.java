package com.turkcell.socceronlinemanagement.business.abstracts;


import com.turkcell.socceronlinemanagement.business.dto.requests.PaymentRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.PaymentResponse;
import com.turkcell.socceronlinemanagement.common.dto.CreateTransferPaymentRequest;

import java.util.List;

public interface PaymentService {
    List<PaymentResponse> getAll();

    PaymentResponse getById(int id);

    PaymentResponse add(PaymentRequest request);

    PaymentResponse update(int id, PaymentRequest request);

    void delete(int id);

    void processTransferPayment(CreateTransferPaymentRequest request);

}
