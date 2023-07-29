package com.turkcell.socceronlinemanagement.service.payment;


import com.turkcell.socceronlinemanagement.common.dto.CreateTransferPaymentRequest;
import com.turkcell.socceronlinemanagement.model.Payment;
import com.turkcell.socceronlinemanagement.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentManager implements PaymentService {
    private final PaymentRepository repository;
    private final ModelMapper mapper;
    private final PosService posService;
    private final PaymentBusinessRules rules;

    @Override
    public List<PaymentResponse> getAll() {
        List<Payment> payments = repository.findAll();
        List<PaymentResponse> response = payments
                .stream()
                .map(payment -> mapper.map(payment, PaymentResponse.class))
                .toList();

        return response;
    }

    @Override
    public PaymentResponse getById(Integer id) {
        rules.checkIfPaymentExists(id);
        Payment payment = repository.findById(id).orElseThrow();
        PaymentResponse response = mapper.map(payment, PaymentResponse.class);

        return response;
    }

    @Override
    public PaymentResponse add(PaymentRequest request) {
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(0);
        repository.save(payment);
        PaymentResponse response = mapper.map(payment, PaymentResponse.class);

        return response;
    }

    @Override
    public PaymentResponse update(Integer id, PaymentRequest request) {
        rules.checkIfPaymentExists(id);
        Payment payment = mapper.map(request, Payment.class);
        payment.setId(id);
        repository.save(payment);
        PaymentResponse response = mapper.map(payment, PaymentResponse.class);

        return response;
    }

    @Override
    public void delete(Integer id) {
        rules.checkIfPaymentExists(id);
        repository.deleteById(id);
    }

    @Override
    public void processTransferPayment(CreateTransferPaymentRequest request) {
        rules.checkIfPaymentIsValid(request);
        Payment payment = repository.findByBalance(request.getBalance());
        rules.checkIfBalanceIsEnough(request.getPlayerMarketValue(), payment.getBalance());
        posService.pay(); // fake pos service
        payment.setBalance(payment.getBalance().subtract(request.getPlayerMarketValue()));
        repository.save(payment);
    }
}
