package com.turkcell.socceronlinemanagement.api;


import com.turkcell.socceronlinemanagement.service.payment.PaymentService;
import com.turkcell.socceronlinemanagement.service.payment.PaymentRequest;
import com.turkcell.socceronlinemanagement.service.payment.PaymentResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService service;

    @GetMapping
    public List<PaymentResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PaymentResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public PaymentResponse add(@Valid @RequestBody PaymentRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public PaymentResponse update(@PathVariable int id, @Valid @RequestBody PaymentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
