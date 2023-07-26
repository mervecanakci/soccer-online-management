package com.turkcell.socceronlinemanagement.business.rules;

import com.turkcell.socceronlinemanagement.common.constants.Messages;
import com.turkcell.socceronlinemanagement.core.exceptions.BusinessException;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferBusinessRules {
    private final TransferRepository repository;

    public void checkIfTransferExistsById(int id) {
        if (!repository.existsById(id)) {
            throw new BusinessException(Messages.Transfer.NOT_EXISTS);
        }
    }

    public void checkIfPlayerIsNotUnderTransfer(int playerId) {
        if (!repository.existsByPlayerIdAndIsCompletedIsFalse(playerId)) {
            throw new BusinessException(Messages.Transfer.PLAYER_NOT_EXISTS);
        }
    }

    public void checkIfPlayerUnderTransfer(int playerId) {
        if (repository.existsByPlayerIdAndIsCompletedIsFalse(playerId)) {
            throw new BusinessException(Messages.Transfer.PLAYER_EXISTS);
        }
    }

    public void checkPlayerAvailabilityForTransfer(TransferState transferState) {
        if (transferState.equals(TransferState.TRANSFERRED)) {
            throw new BusinessException(Messages.Transfer.PLAYER_EXISTS);
        }
    }//todo sanki mantıklı ve gerekli değil gibi

}
