package com.turkcell.socceronlinemanagement.service.player;


import com.github.javafaker.Faker;
import com.turkcell.socceronlinemanagement.model.Player;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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
    public PlayerResponse getById(Integer id) {
        rules.checkIfPlayerExistsById(id);
        Player player = repository.findById(id).orElseThrow();
        PlayerResponse response = mapper.map(player, PlayerResponse.class);

        return response;
    }

    @Override
    @Transactional
    public List<PlayerResponse> add(PlayerRequest request) {
        List<Player> players = generatePlayers();
        List<PlayerResponse> responses = new ArrayList<>();

        for (Player player : players) {
            player.setMarketValue(request.getMarketValue()); // Player'ların getMarketValue PlayerRequest'ten alınacak
            player.setPosition(request.getRandomPosition()); // Player'ların pozisyonu PlayerRequest'ten alınacak

            repository.save(player);
            PlayerResponse response = mapper.map(player, PlayerResponse.class);
            responses.add(response);
    }
        return responses;
    }

    @Override
    public PlayerResponse update(Integer id, PlayerRequest request) {
        rules.checkIfPlayerExistsById(id);
        Player player = mapper.map(request, Player.class);
        player.setId(0);
        player.setTransferState(TransferState.NOT_TRANSFERRED);
        repository.save(player);
        PlayerResponse response = mapper.map(player, PlayerResponse.class);

        return response;
    }

    @Override
    public void delete(Integer id) {
        rules.checkIfPlayerExistsById(id);
        repository.deleteById(id);
    }

    @Override
    public void changeTransferState(Integer playerId, TransferState transferState) {
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

//    public Player createTeamForUser() {
//        Player player = new Player();
//
//        createPlayers(Position.GOALKEEPER, 3);
//        createPlayers(Position.DEFENDER, 6);
//        createPlayers(Position.MIDFIELDER, 6);
//        createPlayers(Position.ATTACKER, 5);
//
//        return player;
//    }

//    private List<Player> createPlayers(Position position, Integer count) {
//        List<Player> players = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            Player player = new Player();
//            player.setPosition(position);
//            players.add(player);
//        }
//        return players;
//    }

    public List<Player> generatePlayers() {
            List<Player> players  = new ArrayList<>();
            int playerCount = 20; // Sabit olarak 20 oyuncu oluşturacak

            for (int i = 0; i < playerCount; i++) {
                Player player = new Player();
                // Burada JavaFaker kullanarak oyuncu adı, yaş, takım gibi alanları rastgele oluşturdun
                player.setFirstName(Faker.instance().name().firstName());
                player.setLastName(Faker.instance().name().lastName());
                player.setCountry(Faker.instance().country().name());
                player.setTransferState(TransferState.NOT_TRANSFERRED);
                player.setAge(Faker.instance().number().numberBetween(18, 40));
                players.add(player);
            }
            return players;
}}
