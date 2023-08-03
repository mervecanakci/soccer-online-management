package com.turkcell.socceronlinemanagement.service.user;

import com.turkcell.socceronlinemanagement.model.User;
import com.turkcell.socceronlinemanagement.repository.UserRepository;
import com.turkcell.socceronlinemanagement.security.filter.JwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserImpl  implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper mapper;
    private final UserBusinessRules rules;
    public UserResponse register(UserRegisterRequest request) { //  kullanıcı kayıt edilir
        var user = new User();
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // şifre encode edilir. encode şifreleme işlemidir.
        user.setId(0);
        //user.setId(null); böyleydi üstteki gibi, yaptın
        var jwtToken = jwtService.generateToken(user);
        repository.save(user);
        return new UserResponse(jwtToken,user);

    }

    public UserResponse authenticate(UserAuthRequest request) { // authenticate: kullanıcı doğrulanır
        User user;
        String jwtToken;
        HashMap<String, Object> claims = new HashMap<>(); // kullanıcıya ait roller claims içerisine ekle
        try {
            user = repository.findByEmail(request.getEmail()).orElseThrow(); // kullanıcı adı ile kullanıcı bulunur
            for (var role : user.getAuthorities()) {
                claims.put("roles", role.toString());
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())); // kullanıcı adı ve şifre ile token oluşturulur
            jwtToken = jwtService.generateToken(claims, user); // kullanıcıya ait token oluşturulur
        } catch (Exception e) { // kullanıcı adı veya şifre hatalı ise
           // log.error(ExceptionTypes.Exception.Auth+" "+e.getMessage());
            throw new RuntimeException("Kullanıcı doğrulanamadı!"); // kullanıcı doğrulanamadı hatası fırlatılır
        }
        return new UserResponse(jwtToken, user);
    }
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
    public UserResponse add(UserAuthRequest request) {
        User user = mapper.map(request, User.class);
        user.setId(0);
        repository.save(user);
        UserResponse response = mapper.map(user, UserResponse.class);

        return response;
    }
    @Override
    public UserResponse update(int id, UserAuthRequest request) {
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
// feride
// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZXJpZGUiLCJpYXQiOjE2OTEwMTMzMzIsImV4cCI6MTY5MTAxNDc3Mn0.CocOo2EK5lPjAV6RF_fxQ-mi4TqfyPekhp8TXC2g69Y
//merve
// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZXJ2ZSIsImlhdCI6MTY5MTAxMzI5NSwiZXhwIjoxNjkxMDE0NzM1fQ.3Hv8UMglAJcwl9Yv9MRMgTzNS-4AhTLjL8xA6kdNNWc