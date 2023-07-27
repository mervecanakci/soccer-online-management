package com.turkcell.socceronlinemanagement.business.concretes;


import com.turkcell.socceronlinemanagement.business.abstracts.PlayerService;
import com.turkcell.socceronlinemanagement.business.abstracts.UserService;
import com.turkcell.socceronlinemanagement.business.dto.requests.UserRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.UserResponse;
import com.turkcell.socceronlinemanagement.business.rules.UserBusinessRules;
import com.turkcell.socceronlinemanagement.model.User;
import com.turkcell.socceronlinemanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;
    private final UserBusinessRules rules;
    private final PlayerManager playerManager;
    private final PlayerService playerService;
    @Override
    public List<UserResponse> getAll() {
        List<User> users = repository.findAll();
        List<UserResponse> response = users
                .stream()
                .map(user -> mapper.map(user, UserResponse.class))
                .toList();

        return response;
    }

    @Override
    public UserResponse getById(int id) {
        rules.checkIfUserExistsById(id);
        User user = repository.findById(id).orElseThrow();
        UserResponse response = mapper.map(user, UserResponse.class);

        return response;
    }

    @Override
    public UserResponse add(UserRequest request) {
        //   rules.checkIfUserExistsByEmail(request.getEmail()); //todo bu ve alttaki aynı çalıştır bak sil birini
        rules.checkIfEmailExists(request);
        User user = mapper.map(request, User.class);
        user.setId(0);
        repository.save(user);
       //todo playerService.add(playerResponse);
        //teamService.add(user);
        UserResponse response = mapper.map(user, UserResponse.class);

        return response;
    }

    @Override
    public UserResponse update(int id, UserRequest request) {
        rules.checkIfUserExistsById(id);
        User user = mapper.map(request, User.class);
        user.setId(id);
        repository.save(user);
        UserResponse response = mapper.map(user, UserResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfUserExistsById(id);
        repository.deleteById(id);
    }

/*

    public void createTeamForUser(User user) {
        Team team = new Team();

        team.setGoalkeepers(createPlayers(Position.GOALKEEPER, 3));
        team.setDefenders(createPlayers(Position.DEFENDER, 6));
        team.setMidfielders(createPlayers(Position.MIDFIELDER, 6));
        team.setAttackers(createPlayers(Position.ATTACKER, 5));

        user.setTeam(team);
    }

    private List<Player> createPlayers(Position position, int count) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Player player = new Player();
            player.setPosition(position);
            players.add(player);
        }
        return players;
    }*/
}
