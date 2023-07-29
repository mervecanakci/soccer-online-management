package com.turkcell.socceronlinemanagement.controllers.api;

import com.turkcell.socceronlinemanagement.service.team.TeamService;
import com.turkcell.socceronlinemanagement.service.team.TeamRequest;
import com.turkcell.socceronlinemanagement.service.team.TeamResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService service;

    @GetMapping
    public List<TeamResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TeamResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponse add(@Valid @RequestBody TeamRequest request) {

        return service.add(request);
    }

    @PutMapping("/{id}")
    public TeamResponse update(@PathVariable int id, @RequestBody TeamRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }


}
