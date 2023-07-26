package com.turkcell.socceronlinemanagement.business.concretes;

import com.turkcell.socceronlinemanagement.business.abstracts.TeamService;
import com.turkcell.socceronlinemanagement.business.dto.requests.TeamRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.TeamResponse;
import com.turkcell.socceronlinemanagement.business.rules.TeamBusinessRules;
import com.turkcell.socceronlinemanagement.model.Player;
import com.turkcell.socceronlinemanagement.model.Team;
import com.turkcell.socceronlinemanagement.model.enums.Position;
import com.turkcell.socceronlinemanagement.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TeamManager implements TeamService {
    private final TeamRepository repository;
    private final ModelMapper mapper;
    private final TeamBusinessRules rules;

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
    public TeamResponse add(TeamRequest request) {
        Team team = mapper.map(request, Team.class);
        team.setId(0);
        repository.save(team);
        TeamResponse response = mapper.map(team, TeamResponse.class);

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

    public Team createTeamForUser() {
        Team team = new Team();

        team.setPlayers(createPlayers(Position.GOALKEEPER, 3));
        team.getPlayers().addAll(createPlayers(Position.DEFENDER, 6));
        team.getPlayers().addAll(createPlayers(Position.MIDFIELDER, 6));
        team.getPlayers().addAll(createPlayers(Position.ATTACKER, 5));

        return team;
    }

    private List<Player> createPlayers(Position position, int count) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Player player = new Player();
            player.setPosition(position);
            players.add(player);
        }
        return players;
    }
}








