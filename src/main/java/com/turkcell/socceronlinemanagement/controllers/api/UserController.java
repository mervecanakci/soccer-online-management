package com.turkcell.socceronlinemanagement.controllers.api;

import com.turkcell.socceronlinemanagement.business.abstracts.UserService;
import com.turkcell.socceronlinemanagement.business.concretes.TeamManager;
import com.turkcell.socceronlinemanagement.business.dto.requests.UserRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.UserResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    private final TeamManager manager;

    @GetMapping
    public List<UserResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse add(@Valid @RequestBody UserRequest request) {
//        Team team = manager.createTeamForUser();
//        request.setTeam(team);
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable int id, @RequestBody UserRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }



   /* @GetMapping("/index")
    public String index(){
        return "ındex sayfası, ";
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return "login basarili dashboard sayfası";
    }
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello from our api");
}
    @GetMapping("/say-good-bye")
    public ResponseEntity<String> sayGoodBye() {
        return ResponseEntity.ok("good bye and see u later");
    } */


}
