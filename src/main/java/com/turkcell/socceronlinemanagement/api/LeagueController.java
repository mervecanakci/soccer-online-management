package com.turkcell.socceronlinemanagement.api;

import com.turkcell.socceronlinemanagement.service.league.LeagueService;
import com.turkcell.socceronlinemanagement.service.league.LeagueRequest;
import com.turkcell.socceronlinemanagement.service.league.LeagueResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/luagues")
public class LeagueController {

    private final LeagueService service;

    @GetMapping
    public List<LeagueResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public LeagueResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeagueResponse add(@Valid @RequestBody LeagueRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public LeagueResponse update(@PathVariable int id, @RequestBody LeagueRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}
