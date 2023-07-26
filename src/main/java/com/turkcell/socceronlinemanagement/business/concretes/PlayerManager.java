package com.turkcell.socceronlinemanagement.business.concretes;


import com.turkcell.socceronlinemanagement.business.abstracts.PlayerService;
import com.turkcell.socceronlinemanagement.business.dto.requests.PlayerRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.PlayerResponse;
import com.turkcell.socceronlinemanagement.business.rules.PlayerBusinessRules;
import com.turkcell.socceronlinemanagement.model.Player;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerManager implements PlayerService {
    private final PlayerRepository repository;
    private final ModelMapper mapper;
    private final PlayerBusinessRules rules;

    @Override
    public List<PlayerResponse> getAll(boolean includeTransfer) {
        List<Player> players = filterPlayersByTransferState(includeTransfer);
        List<PlayerResponse> response = players
                .stream()
                .map(player -> mapper.map(player, PlayerResponse.class))
                .toList();

        return response;
    }

    @Override
    public PlayerResponse getById(int id) {
        rules.checkIfPlayerExistsById(id);
        Player player = repository.findById(id).orElseThrow();
        PlayerResponse response = mapper.map(player, PlayerResponse.class);

        return response;
    }

    @Override
    public PlayerResponse add(PlayerRequest request) {
        Player player = mapper.map(request, Player.class);
        player.setId(0);
        repository.save(player);
        PlayerResponse response = mapper.map(player, PlayerResponse.class);

        return response;
    }

    @Override
    public PlayerResponse update(int id, PlayerRequest request) {
        rules.checkIfPlayerExistsById(id);
        Player player = mapper.map(request, Player.class);
        player.setId(0);
        player.setTransferState(TransferState.NOT_TRANSFERRED);
        repository.save(player);
        PlayerResponse response = mapper.map(player, PlayerResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfPlayerExistsById(id);
        repository.deleteById(id);
    }

    @Override
    public void changeTransferState(int playerId, TransferState transferState) {
        Player player = repository.findById(playerId).orElseThrow();
        player.setTransferState(transferState);
        repository.save(player);
    }

    private List<Player> filterPlayersByTransferState(boolean includeTransfer) {
        // includeTransfer ; transfer listesinde olanları dahil edeyim mi diyor
        //true ise hepsini getir
        if (includeTransfer) {
            return repository.findAll();
        }
        // false ise transfer listesini çıkarıp diğerlerini getiricek
        return repository.findAllByTransferStateIsNot(TransferState.TRANSFERRED);
    }

//    private double getIncreaseMarketValue(Player player) {
//        double increasePercentage = 10 + (100 - 10) * random.nextDouble(); //artış yüzdesi
//        double increasedMarketValue = player.getMarketValue() + (player.getMarketValue() * increasePercentage / 100); //artan değer
//        player.setMarketValue(increasedMarketValue);
//        return increasedMarketValue;
//    }














    /*
        private double getTotalMarketValue(Player player) {
            return player.getMarketValue() * player.getRentedForDays();
        }

    private void transferArtir(Player player) {
        Random random = new Random();
        int minArtisYuzdesi = 10; // Minimum artış yüzdesi (%10)
        int maxArtisYuzdesi = 100; // Maksimum artış yüzdesi (%100)

        // Rastgele bir artış yüzdesi hesaplamak için nextInt() metodu kullanılır.
        // nextInt(max - min + 1) ifadesi, min ve max arasında (max dahil) rastgele bir sayı üretir.
        int rastgeleArtisYuzdesi = random.nextInt(maxArtisYuzdesi - minArtisYuzdesi + 1) + minArtisYuzdesi;

        // Artış yüzdesine göre değeri güncelleme
        double artisMiktari = player.getMarketValue() * rastgeleArtisYuzdesi / 100.0;
        player.getMarketValue() += artisMiktari;
    }
*/


}
