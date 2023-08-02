package com.turkcell.socceronlinemanagement.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class DenemeController {

}
//
//    private final UserService service;
//
//    private final TeamManager manager;
//
//    @GetMapping
//    public List<UserResponse> getAll() {
//        return service.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public UserResponse getById(@PathVariable int id) {
//        return service.getById(id);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserResponse add(@Valid @RequestBody UserRequest request) {
////        Team team = manager.createTeamForUser();
////        request.setTeam(team);
//        return service.add(request);
//    }
//
//    @PutMapping("/{id}")
//    public UserResponse update(@PathVariable int id, @RequestBody UserRequest request) {
//        return service.update(id, request);
//    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable int id) {
//        service.delete(id);
//    }
//
//}
