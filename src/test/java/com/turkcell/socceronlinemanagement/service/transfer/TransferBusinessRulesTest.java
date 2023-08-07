package com.turkcell.socceronlinemanagement.service.transfer;

import com.turkcell.socceronlinemanagement.advice.exception.BusinessException;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.TransferRepository;
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

@ContextConfiguration(classes = {TransferBusinessRules.class})
@ExtendWith(SpringExtension.class)
class TransferBusinessRulesTest {
    @Autowired
    private TransferBusinessRules transferBusinessRules;

    @MockBean
    private TransferRepository transferRepository;

    @Test
    void testCheckIfTransferExistsById() {
        when(transferRepository.existsById(Mockito.<Integer>any())).thenReturn(true);
        transferBusinessRules.checkIfTransferExistsById(1);
        verify(transferRepository).existsById(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfTransferExistsById2() {
        when(transferRepository.existsById(Mockito.<Integer>any())).thenReturn(false);
        assertThrows(BusinessException.class, () -> transferBusinessRules.checkIfTransferExistsById(1));
        verify(transferRepository).existsById(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfTransferExistsById3() {
        when(transferRepository.existsById(Mockito.<Integer>any())).thenThrow(new BusinessException("An error occurred"));
        assertThrows(BusinessException.class, () -> transferBusinessRules.checkIfTransferExistsById(1));
        verify(transferRepository).existsById(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfPlayerIsNotUnderTransfer() {
        when(transferRepository.existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any())).thenReturn(true);
        transferBusinessRules.checkIfPlayerIsNotUnderTransfer(1);
        verify(transferRepository).existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfPlayerIsNotUnderTransfer2() {
        when(transferRepository.existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any())).thenReturn(false);
        assertThrows(BusinessException.class, () -> transferBusinessRules.checkIfPlayerIsNotUnderTransfer(1));
        verify(transferRepository).existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfPlayerIsNotUnderTransfer3() {
        when(transferRepository.existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any()))
                .thenThrow(new BusinessException("An error occurred"));
        assertThrows(BusinessException.class, () -> transferBusinessRules.checkIfPlayerIsNotUnderTransfer(1));
        verify(transferRepository).existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfPlayerUnderTransfer() {
        when(transferRepository.existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any())).thenReturn(true);
        assertThrows(BusinessException.class, () -> transferBusinessRules.checkIfPlayerUnderTransfer(1));
        verify(transferRepository).existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfPlayerUnderTransfer2() {
        when(transferRepository.existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any())).thenReturn(false);
        transferBusinessRules.checkIfPlayerUnderTransfer(1);
        verify(transferRepository).existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any());
    }

    @Test
    void testCheckIfPlayerUnderTransfer3() {
        when(transferRepository.existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any()))
                .thenThrow(new BusinessException("An error occurred"));
        assertThrows(BusinessException.class, () -> transferBusinessRules.checkIfPlayerUnderTransfer(1));
        verify(transferRepository).existsByPlayerIdAndIsCompletedIsFalse(Mockito.<Integer>any());
    }

    @Test
    void testCheckPlayerAvailabilityForTransfer() {
        assertThrows(BusinessException.class,
                () -> transferBusinessRules.checkPlayerAvailabilityForTransfer(TransferState.TRANSFERRED));
    }

    @Test
    void testCheckPlayerAvailabilityForTransfer2() {
        transferBusinessRules.checkPlayerAvailabilityForTransfer(TransferState.NOT_TRANSFERRED);
    }

    @Test
    void testCheckIfBalanceIsEnough() {
        transferBusinessRules.checkIfBalanceIsEnough(10.0d, 10.0d);
    }

    @Test
    void testCheckIfBalanceIsEnough2() {
        assertThrows(BusinessException.class, () -> transferBusinessRules.checkIfBalanceIsEnough(10.0d, 0.5d));
    }
}

