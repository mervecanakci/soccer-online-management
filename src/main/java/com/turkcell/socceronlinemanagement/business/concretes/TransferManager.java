package com.turkcell.socceronlinemanagement.business.concretes;

import com.turkcell.socceronlinemanagement.business.abstracts.PaymentService;
import com.turkcell.socceronlinemanagement.business.abstracts.PlayerService;
import com.turkcell.socceronlinemanagement.business.abstracts.TransferService;
import com.turkcell.socceronlinemanagement.business.dto.requests.PlayerRequest;
import com.turkcell.socceronlinemanagement.business.dto.requests.TransferRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.PlayerResponse;
import com.turkcell.socceronlinemanagement.business.dto.responses.TransferResponse;
import com.turkcell.socceronlinemanagement.business.rules.TransferBusinessRules;
import com.turkcell.socceronlinemanagement.common.dto.CreateTransferPaymentRequest;
import com.turkcell.socceronlinemanagement.model.Transfer;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.TransferRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


@Service
@AllArgsConstructor
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
    public TransferResponse getById(int id) {
        rules.checkIfTransferExistsById(id);
        Transfer transfer = repository.findById(id).orElseThrow();
        TransferResponse response = mapper.map(transfer, TransferResponse.class);

        return response;
    }

    @Override
    public TransferResponse returnPlayerFromTransfer(int playerId) {
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
        transfer.setId(0);
        transfer.setCompleted(false);
        transfer.setDateOfTransfer(LocalDateTime.now());

        // payment
        CreateTransferPaymentRequest paymentRequest = new CreateTransferPaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
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
    public TransferResponse update(int id, TransferRequest request) {
        rules.checkIfTransferExistsById(id);
        Transfer transfer = mapper.map(request, Transfer.class);
        transfer.setId(id);
        transfer.setPlayerMarketValue(processTransfer(transfer));
        repository.save(transfer);
        TransferResponse response = mapper.map(transfer, TransferResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfTransferExistsById(id);
        makePlayerNotTransferIfIsCompletedFalse(id);
        int playerId = repository.findById(id).get().getPlayer().getId();
        playerService.changeTransferState(playerId, TransferState.NOT_TRANSFERRED);
        repository.deleteById(id);
    }

    private void makePlayerNotTransferIfIsCompletedFalse(int id) {
        int playerId = repository.findById(id).get().getPlayer().getId();
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


    public double processTransfer(Transfer transfer) {
        // Futbolcu nesnesinin mevcut market value'sini alın
        double currentMarketValue = transfer.getPlayerMarketValue();
        // Rastgele artış yüzdesi oluşturun (örneğin, 10% ile 100% arasında)
        double increasePercentage = 10 + (100 - 10) * random.nextDouble();
        // Yeni market value değerini hesaplayın ve güncelleyin
        double increasedMarketValue = currentMarketValue + (currentMarketValue * increasePercentage / 100);
        transfer.setPlayerMarketValue(increasedMarketValue);

        return increasedMarketValue; //bu mu
    }

}


//•	Bir kullanıcı bir oyuncuyu transfer listesine aldığında, bu oyuncu için istenen fiyatı/değeri belirlemelidir.
// Bu değer bir pazar listesinde listelenmelidir. Başka bir kullanıcı/takım bu oyuncuyu satın aldığında,
// bu fiyattan satın alınmalıdır.

/**
 * todo filtreleme yap
 * Her kullanıcı bir transfer listesindeki tüm oyuncuları görebilmeli ve onları ülkeye, takım adına, oyuncu adına ve bir değere göre filtreleyebilmelidir
 */