package com.turkcell.socceronlinemanagement.service.team;


import com.turkcell.socceronlinemanagement.model.Player;
import com.turkcell.socceronlinemanagement.model.Team;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.PlayerRepository;
import com.turkcell.socceronlinemanagement.repository.TeamRepository;
import com.turkcell.socceronlinemanagement.repository.TransferRepository;
import com.turkcell.socceronlinemanagement.service.player.*;
import com.turkcell.socceronlinemanagement.service.transfer.TransferBusinessRules;
import com.turkcell.socceronlinemanagement.service.transfer.TransferPlayerRequest;
import com.turkcell.socceronlinemanagement.service.transfer.TransferService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TeamImpl implements TeamService {
    private final TeamRepository repository;
    private final ModelMapper mapper;
    private final TeamBusinessRules rules;
    private final PlayerImpl playerManager;
    private final PlayerRepository playerRepository;
    private final TeamBusinessRules teamBusinessRules;
    private final TransferBusinessRules transferBusinessRules;
    private final TransferRepository transferRepository;
    private final PlayerBusinessRules playerBusinessRules;
    private final TeamRepository teamRepository;
    private final PlayerService playerService;
    private final Random random;

    @Override
    public List<TeamResponse> getAll() {
        List<Team> teams = repository.findAll();
        List<TeamResponse> response = teams
                .stream()
                .map(team -> mapper.map(team, TeamResponse.class))
                .toList();

        return response;
    }

    @Override
    public TeamResponse getById(int id) {
        rules.checkIfTeamExistsById(id);
        Team team = repository.findById(id).orElseThrow();
        TeamResponse response = mapper.map(team, TeamResponse.class);

        return response;
    }

    @Override
    @Transactional
    public TeamResponse add(TeamRequest request) {
        Team team = mapper.map(request, Team.class);
        team.setId(0);
        repository.save(team);
        TeamResponse response = mapper.map(team, TeamResponse.class);

        PlayerRequest playerRequest = new PlayerRequest();
        List<PlayerResponse> playersForTeam = playerManager.add(playerRequest); //playerManager.add(playerRequest) ile playerManager dan playerRequest i alıyoruz
        for (PlayerResponse playerResponse : playersForTeam) {
            team.setTeamValue(team.getTeamValue() + playerRequest.getMarketValue());
            Player player = mapper.map(playerResponse, Player.class);
            player.setTeam(team);
            playerRepository.save(player);
        }

        return response;
    }


    @Override
    public TeamResponse update(int id, TeamRequest request) {
        rules.checkIfTeamExistsById(id);
        Team team = mapper.map(request, Team.class);
        team.setId(id);
        repository.save(team);
        TeamResponse response = mapper.map(team, TeamResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfTeamExistsById(id);
        repository.deleteById(id);
    }

    @Override
    public TeamResponse addTransferPlayer(@Valid TransferPlayerRequest request) {
        playerBusinessRules.checkIfPlayerExistsById(request.getPlayerId());
        transferBusinessRules.checkIfTransferExistsById(request.getPlayerId());
        teamBusinessRules.checkIfTeamExistsById(request.getTeamId());
        final double marketValue = playerRepository.findById(request.getPlayerId()).get().getMarketValue(); //marketValue yu playerRepository den çekiyoruz
        transferBusinessRules.checkIfBalanceIsEnough(marketValue, request.getPrice()); //takımıın yeterli bakiyesi var mı diye kontrol ediyoruz
        final Player player = playerRepository.findById(request.getPlayerId()).get(); //playerId ile playerRepository den player çekiyoruz
        final Team team = teamRepository.findById(request.getTeamId()).get(); //teamId ile teamRepository den team çekiyoruz
        playerService.changeTransferState(request.getPlayerId(), TransferState.NOT_TRANSFERRED);
       teamRepository.findById(request.getTeamId()).get().setTeamValue(team.getTeamValue() - request.getPrice()); //takımın bakiyesini güncelliyoruz
       teamRepository.findById(player.getTeam().getId()).get().setTeamValue(team.getTeamValue() + request.getPrice()); //takımın bakiyesini güncelliyoruz
        player.setTeam(team); //player ın takımını setliyoruz
        transferRepository.delete(transferRepository.findByPlayerId(request.getPlayerId())); //transferRepository den player ı siliyoruz

        double increasedMarketValue = getIncreasedMarketValue(request); //artan marketValue yu hesaplıyoruz
        player.setMarketValue(increasedMarketValue); //player ın marketValue sunu setliyoruz
        this.playerRepository.save(player); //player ı kaydediyoruz
        return mapper.map(team, TeamResponse.class); //team ı döndürüyoruz
    }

        private double getIncreasedMarketValue(TransferPlayerRequest request) {
        double increasePercentage = 0.10 + (1.00 - 0.10) * random.nextDouble();
        double increaseAmount = request.getPrice() * increasePercentage ;
        System.out.println("increaseAmount = " + increaseAmount);
            return (int) (request.getPrice() + increaseAmount);

    }
//private double getIncreasedMarketValue(TransferPlayerRequest request) {
//        double currentMarketValue = request.getPlayerMarketValue();
//        double increasePercentage = 10 + (100 - 10) * random.nextDouble();
//        double increaseAmount = currentMarketValue * increasePercentage / 100;
//        double increasedMarketValue = currentMarketValue + increaseAmount;
//        request.setPlayerMarketValue(increasedMarketValue);
//        return increasedMarketValue;
//}

//    public double getProcessTransfer(Transfer transfer) {
//        double currentMarketValue = transfer.getPlayerMarketValue();
//        double increasePercentage = 10 + (100 - 10) * random.nextDouble();
//        double increaseAmount = currentMarketValue * increasePercentage / 100;
//        double increasedMarketValue = currentMarketValue + increaseAmount;
//        transfer.setPlayerMarketValue(increasedMarketValue);
//
//        return increasedMarketValue;
//    }

//    private List<Team> generateTeam() {
//        List<Team> teams = new ArrayList<>();
//        Team team = new Team();
//        team.setTeamName(Faker.instance().team().name());
//        team.setTeamCountry(Faker.instance().address().country());
//        teams.add(team);
//        return teams;
//    }

}








