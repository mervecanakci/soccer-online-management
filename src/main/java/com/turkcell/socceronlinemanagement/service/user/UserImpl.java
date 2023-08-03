package com.turkcell.socceronlinemanagement.service.user;

import com.turkcell.socceronlinemanagement.model.User;
import com.turkcell.socceronlinemanagement.repository.UserRepository;
import com.turkcell.socceronlinemanagement.security.filter.JwtService;
import com.turkcell.socceronlinemanagement.service.team.TeamService;
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
    private final TeamService teamService;

    public UserResponse register(UserRegisterRequest request) { //  kullanıcı kayıt edilir
        var user = new User();
        user.setRole(request.getRole());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // şifre encode edilir. encode şifreleme işlemidir.
        //user.setId(null); böyleydi üstteki gibi, yaptın
        var jwtToken = jwtService.generateToken(user);
        repository.save(user);
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setToken(jwtToken);
        response.setUser(user);
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setTeams(teamService.getAll());

        return response;
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
      //  UserResponse response = mapper.map(user, UserResponse.class);
      UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setToken(jwtToken);
        response.setUser(user);
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setTeams(teamService.getAll());

        return response;
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
        rules.checkIfUserExistsByEmail(request.getEmail());
        User user = mapper.map(request, User.class);
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
    }
