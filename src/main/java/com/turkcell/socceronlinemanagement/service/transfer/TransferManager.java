package com.turkcell.socceronlinemanagement.service.transfer;

import com.turkcell.socceronlinemanagement.service.payment.PaymentService;
import com.turkcell.socceronlinemanagement.service.player.PlayerService;
import com.turkcell.socceronlinemanagement.service.player.PlayerRequest;
import com.turkcell.socceronlinemanagement.service.player.PlayerResponse;
import com.turkcell.socceronlinemanagement.common.dto.CreateTransferPaymentRequest;
import com.turkcell.socceronlinemanagement.model.Transfer;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class TransferManager implements TransferService {
    private final PlayerService playerService;
    private final TransferRepository repository;
    private final ModelMapper mapper;
    private final PaymentService paymentService;
    private final TransferBusinessRules rules;
    private Random random;

    @Override
    public List<TransferResponse> getAll() {
        List<Transfer> transfers = repository.findAll();
        List<TransferResponse> response = transfers
                .stream()
                .map(transfer -> mapper.map(transfer, TransferResponse.class))
                .toList();

        return response;
    }

    @Override
    public TransferResponse getById(Integer id) {
        rules.checkIfTransferExistsById(id);
        Transfer transfer = repository.findById(id).orElseThrow();
        TransferResponse response = mapper.map(transfer, TransferResponse.class);

        return response;
    }

    @Override
    public TransferResponse returnPlayerFromTransfer(Integer playerId) {
        rules.checkIfPlayerIsNotUnderTransfer(playerId);
        Transfer transfer = repository.findByPlayerIdAndIsCompletedIsFalse(playerId);
        transfer.setCompleted(true);
        repository.save(transfer);
        playerService.changeTransferState(playerId, TransferState.NOT_TRANSFERRED);
        TransferResponse response = mapper.map(transfer, TransferResponse.class);

        return response;
    }

    @Override
    public TransferResponse add(TransferRequest request) {
        rules.checkIfPlayerUnderTransfer(request.getPlayerId());
        rules.checkPlayerAvailabilityForTransfer(playerService.getById(request.getPlayerId()).getTransferState());
        Transfer transfer = mapper.map(request, Transfer.class);
        //  transfer.setId(0);
        transfer.setCompleted(false);
        transfer.setDateOfTransfer(LocalDateTime.now());
        request.builder().playerId(0).build(); //todo

        // payment
        CreateTransferPaymentRequest paymentRequest = new CreateTransferPaymentRequest();
        mapper.map(request.getPaymentRequest().getBalance(), paymentRequest);
        paymentRequest.setPlayerMarketValue(processTransfer(transfer)); //sadece transfer mi yazmak lazımdıı
        paymentService.processTransferPayment(paymentRequest);


        repository.save(transfer);
        playerService.changeTransferState(request.getPlayerId(), TransferState.TRANSFERRED);
        TransferResponse response = mapper.map(transfer, TransferResponse.class);

        //todo takım değişmeyi burada service ile yapmıştım olduğundan emin değilim
        PlayerRequest playerRequest = new PlayerRequest();
        createPlayerRequest(request, playerRequest, transfer);
        playerService.add(playerRequest);
        return response;

    }

    @Override
    public TransferResponse update(Integer id, TransferRequest request) {
        rules.checkIfTransferExistsById(id);
        Transfer transfer = mapper.map(request, Transfer.class);
        transfer.setId(id);
        transfer.setPlayerMarketValue(processTransfer(transfer));
        repository.save(transfer);
        TransferResponse response = mapper.map(transfer, TransferResponse.class);

        return response;
    }

    @Override
    public void delete(Integer id) {
        rules.checkIfTransferExistsById(id);
        makePlayerNotTransferIfIsCompletedFalse(id);
        Integer playerId = repository.findById(id).get().getPlayer().getId();
        playerService.changeTransferState(playerId, TransferState.NOT_TRANSFERRED);
        repository.deleteById(id);
    }

    private void makePlayerNotTransferIfIsCompletedFalse(Integer id) {
        Integer playerId = repository.findById(id).get().getPlayer().getId();
        if (repository.existsByPlayerIdAndIsCompletedIsFalse(playerId)) {
            playerService.changeTransferState(playerId, TransferState.NOT_TRANSFERRED);
        }
    }
//delete deki ile aynı işi mi yapıyordu acaba

    private void createPlayerRequest(TransferRequest request, PlayerRequest playerRequest, Transfer transfer) {
        PlayerResponse playerResponse = playerService.getById(request.getPlayerId());
        playerRequest.setMarketValue(transfer.getPlayerMarketValue());
        playerRequest.setTeamId(transfer.getNewTeamId());
        //güncelledim ya değeri playerdaki marketvalue buradaki playermarketvalue gibi ileride yine kullanırsın
    }


    public BigDecimal processTransfer(Transfer transfer) {
        // Futbolcu nesnesinin mevcut market value'sini al
        BigDecimal currentMarketValue = transfer.getPlayerMarketValue();
        // Rastgele artış yüzdesi  (örneğin, 10% ile 100% arasında)
        BigDecimal increasePercentage = BigDecimal.valueOf(10 + (100 - 10) * random.nextDouble());

        // Çarpma işlemi için "multiply()" yöntemi
        BigDecimal increaseAmount = currentMarketValue.multiply(increasePercentage).divide(BigDecimal.valueOf(100));
        // Toplama işlemi için "add()" yöntemi
        BigDecimal increasedMarketValue = currentMarketValue.add(increaseAmount);
        transfer.setPlayerMarketValue(increasedMarketValue);

        return increasedMarketValue;
    }

}


/**
 * todo filtreleme yap
 * Her kullanıcı bir transfer listesindeki tüm oyuncuları görebilmeli ve onları ülkeye, takım adına, oyuncu adına ve bir değere göre filtreleyebilmelidir
 */