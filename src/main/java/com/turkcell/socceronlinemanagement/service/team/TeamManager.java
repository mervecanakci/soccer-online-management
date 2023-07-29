package com.turkcell.socceronlinemanagement.service.team;

import com.turkcell.socceronlinemanagement.model.Player;
import com.turkcell.socceronlinemanagement.model.Team;
import com.turkcell.socceronlinemanagement.model.enums.TransferState;
import com.turkcell.socceronlinemanagement.repository.PlayerRepository;
import com.turkcell.socceronlinemanagement.repository.TeamRepository;
import com.turkcell.socceronlinemanagement.service.player.PlayerManager;
import com.turkcell.socceronlinemanagement.service.player.PlayerRequest;
import com.turkcell.socceronlinemanagement.service.player.PlayerResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamManager implements TeamService {
    private final TeamRepository repository;
    private final ModelMapper mapper;
    private final TeamBusinessRules rules;
private final PlayerManager playerManager;
    private final PlayerRepository playerRepository;

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
    public TeamResponse getById(Integer id) {
        rules.checkIfTeamExistsById(id);
        Team team = repository.findById(id).orElseThrow();
        TeamResponse response = mapper.map(team, TeamResponse.class);

        return response;
    }

    @Override
    @Transactional
    public TeamResponse add(TeamRequest request) {
        // ModelMapper konfigürasyonunu ekleyelim
//        mapper.typeMap(TeamRequest.class, Team.class).addMappings(mapper -> {
//            mapper.map(TeamRequest::getUserId, Team::setId);
//        });
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(TeamRequest.class, Team.class)
                .addMappings(m -> m.skip(Team::setId));

        Team team = mapper.map(request, Team.class);
    //    team.setId(0);
        repository.save(team);
        TeamResponse response = mapper.map(team, TeamResponse.class);

        PlayerRequest playerRequest = new PlayerRequest();
        // Gerekli verilerle playerRequest nesnesini doldurun

        List<PlayerResponse> playersForTeam = playerManager.add(playerRequest);

        for (PlayerResponse playerResponse : playersForTeam) {
            Player player = mapper.map(playerResponse, Player.class);
            player.setTeam(team);
            playerRepository.save(player);
        }

        return response;
    }



    @Override
    public TeamResponse update(Integer id, TeamRequest request) {
        rules.checkIfTeamExistsById(id);
        Team team = mapper.map(request, Team.class);
        team.setId(id);
        repository.save(team);
        TeamResponse response = mapper.map(team, TeamResponse.class);

        return response;
    }

    @Override
    public void delete(Integer id) {
        rules.checkIfTeamExistsById(id);
        repository.deleteById(id);
    }



//    public Team createTeamForUser() {
//        Team team = new Team();
//
//        team.setPlayers(createPlayers(Position.GOALKEEPER, 3));
//        team.getPlayers().addAll(createPlayers(Position.DEFENDER, 6));
//        team.getPlayers().addAll(createPlayers(Position.MIDFIELDER, 6));
//        team.getPlayers().addAll(createPlayers(Position.ATTACKER, 5));
//
//        return team;
//    }
//
//    private List<Player> createPlayers(Position position, int count) {
//        List<Player> players = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            Player player = new Player();
//            player.setPosition(position);
//            players.add(player);
//        }
//        return players;
//    }
}








