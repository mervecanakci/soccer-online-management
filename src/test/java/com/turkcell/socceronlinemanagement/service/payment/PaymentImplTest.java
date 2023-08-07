package com.turkcell.socceronlinemanagement.service.payment;

import com.turkcell.socceronlinemanagement.common.dto.CreateTransferPaymentRequest;
import com.turkcell.socceronlinemanagement.model.Payment;
import com.turkcell.socceronlinemanagement.repository.PaymentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PaymentImpl.class})
@ExtendWith(SpringExtension.class)
class PaymentImplTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PaymentBusinessRules paymentBusinessRules;

    @Autowired
    private PaymentImpl paymentImpl;

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private PosService posService;

    @Test
    void testGetAll() {
        when(paymentRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(paymentImpl.getAll().isEmpty());
        verify(paymentRepository).findAll();
    }

    @Test
    void testGetAll2() {
        Payment payment = new Payment();
        payment.setId(1);
        payment.setPlayerId(1);
        payment.setTeamId(1);
        payment.setTeamValue(10.0d);
        payment.setUserId(1);

        ArrayList<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        when(paymentRepository.findAll()).thenReturn(paymentList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PaymentResponse>>any())).thenReturn(
                new PaymentResponse(1, 1, "Team Name", 1, "Jane", "Doe", "New Team Name", "Old Team Name", 10.0d));
        assertEquals(1, paymentImpl.getAll().size());
        verify(paymentRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<PaymentResponse>>any());
    }

    @Test
    void testGetAll3() {
        Payment payment = new Payment();
        payment.setId(1);
        payment.setPlayerId(1);
        payment.setTeamId(1);
        payment.setTeamValue(10.0d);
        payment.setUserId(1);

        Payment payment2 = new Payment();
        payment2.setId(2);
        payment2.setPlayerId(2);
        payment2.setTeamId(2);
        payment2.setTeamValue(0.5d);
        payment2.setUserId(2);

        ArrayList<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment2);
        paymentList.add(payment);
        when(paymentRepository.findAll()).thenReturn(paymentList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PaymentResponse>>any())).thenReturn(
                new PaymentResponse(1, 1, "Team Name", 1, "Jane", "Doe", "New Team Name", "Old Team Name", 10.0d));
        assertEquals(2, paymentImpl.getAll().size());
        verify(paymentRepository).findAll();
        verify(modelMapper, atLeast(1)).map(Mockito.<Object>any(), Mockito.<Class<PaymentResponse>>any());
    }

    @Test
    void testGetById() {
        Payment payment = new Payment();
        payment.setId(1);
        payment.setPlayerId(1);
        payment.setTeamId(1);
        payment.setTeamValue(10.0d);
        payment.setUserId(1);
        Optional<Payment> ofResult = Optional.of(payment);
        when(paymentRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        PaymentResponse paymentResponse = new PaymentResponse(1, 1, "Team Name", 1, "Jane", "Doe", "New Team Name",
                "Old Team Name", 10.0d);

        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PaymentResponse>>any())).thenReturn(paymentResponse);
        doNothing().when(paymentBusinessRules).checkIfPaymentExists(anyInt());
        assertSame(paymentResponse, paymentImpl.getById(1));
        verify(paymentRepository).findById(Mockito.<Integer>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<PaymentResponse>>any());
        verify(paymentBusinessRules).checkIfPaymentExists(anyInt());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testGetById2() {

        when(paymentRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn("Map");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<PaymentResponse>>any())).thenReturn(
                new PaymentResponse(1, 1, "Team Name", 1, "Jane", "Doe", "New Team Name", "Old Team Name", 10.0d));
        doNothing().when(paymentBusinessRules).checkIfPaymentExists(anyInt());
        paymentImpl.getById(1);
    }

    @Test
    void testAdd() {

        Payment payment = new Payment();
        payment.setId(1);
        payment.setPlayerId(1);
        payment.setTeamId(1);
        payment.setTeamValue(10.0d);
        payment.setUserId(1);
        PaymentRepository repository = mock(PaymentRepository.class);
        when(repository.save(Mockito.<Payment>any())).thenReturn(payment);
        ModelMapper mapper = new ModelMapper();
        PosService posService = mock(PosService.class);
        PaymentImpl paymentImpl = new PaymentImpl(repository, mapper, posService,
                new PaymentBusinessRules(mock(PaymentRepository.class)));
        PaymentResponse actualAddResult = paymentImpl.add(new PaymentRequest(10.0d));
        assertEquals(0, actualAddResult.getId());
        assertEquals(10.0d, actualAddResult.getTeamValue());
        assertEquals(0, actualAddResult.getTeamId());
        assertEquals(0, actualAddResult.getPlayerId());
        verify(repository).save(Mockito.<Payment>any());
    }

    @Test
    void testUpdate() {

        Payment payment = new Payment();
        payment.setId(1);
        payment.setPlayerId(1);
        payment.setTeamId(1);
        payment.setTeamValue(10.0d);
        payment.setUserId(1);
        PaymentRepository repository = mock(PaymentRepository.class);
        when(repository.save(Mockito.<Payment>any())).thenReturn(payment);
        PaymentRepository repository2 = mock(PaymentRepository.class);
        when(repository2.existsById(Mockito.<Integer>any())).thenReturn(true);
        PaymentBusinessRules rules = new PaymentBusinessRules(repository2);
        PaymentImpl paymentImpl = new PaymentImpl(repository, new ModelMapper(), mock(PosService.class), rules);
        PaymentResponse actualUpdateResult = paymentImpl.update(1, new PaymentRequest(10.0d));
        assertEquals(1, actualUpdateResult.getId());
        assertEquals(10.0d, actualUpdateResult.getTeamValue());
        assertEquals(0, actualUpdateResult.getTeamId());
        assertEquals(0, actualUpdateResult.getPlayerId());
        verify(repository).save(Mockito.<Payment>any());
        verify(repository2).existsById(Mockito.<Integer>any());
    }

    @Test
    void testDelete() {
        doNothing().when(paymentRepository).deleteById(Mockito.<Integer>any());
        doNothing().when(paymentBusinessRules).checkIfPaymentExists(anyInt());
        paymentImpl.delete(1);
        verify(paymentRepository).deleteById(Mockito.<Integer>any());
        verify(paymentBusinessRules).checkIfPaymentExists(anyInt());
    }

    @Test
    void testProcessTransferPayment() {
        Payment payment = new Payment();
        payment.setId(1);
        payment.setPlayerId(1);
        payment.setTeamId(1);
        payment.setTeamValue(10.0d);
        payment.setUserId(1);

        Payment payment2 = new Payment();
        payment2.setId(1);
        payment2.setPlayerId(1);
        payment2.setTeamId(1);
        payment2.setTeamValue(10.0d);
        payment2.setUserId(1);
        when(paymentRepository.save(Mockito.<Payment>any())).thenReturn(payment2);
        when(paymentRepository.findByTeamValue(anyDouble())).thenReturn(payment);
        doNothing().when(posService).pay();
        doNothing().when(paymentBusinessRules).checkIfBalanceIsEnough(anyDouble(), anyDouble());
        paymentImpl.processTransferPayment(new CreateTransferPaymentRequest(1, 1, 10.0d, 10.0d));
        verify(paymentRepository).findByTeamValue(anyDouble());
        verify(paymentRepository).save(Mockito.<Payment>any());
        verify(posService).pay();
        verify(paymentBusinessRules).checkIfBalanceIsEnough(anyDouble(), anyDouble());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testProcessTransferPayment2() {

        Payment payment = new Payment();
        payment.setId(1);
        payment.setPlayerId(1);
        payment.setTeamId(1);
        payment.setTeamValue(10.0d);
        payment.setUserId(1);

        Payment payment2 = new Payment();
        payment2.setId(1);
        payment2.setPlayerId(1);
        payment2.setTeamId(1);
        payment2.setTeamValue(10.0d);
        payment2.setUserId(1);
        when(paymentRepository.save(Mockito.<Payment>any())).thenReturn(payment2);
        when(paymentRepository.findByTeamValue(anyDouble())).thenReturn(payment);
        doNothing().when(posService).pay();
        doNothing().when(paymentBusinessRules).checkIfBalanceIsEnough(anyDouble(), anyDouble());
        paymentImpl.processTransferPayment(null);
    }
}

