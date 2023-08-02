package com.turkcell.socceronlinemanagement.api;

import com.turkcell.socceronlinemanagement.service.team.TeamService;
import com.turkcell.socceronlinemanagement.service.team.TeamRequest;
import com.turkcell.socceronlinemanagement.service.team.TeamResponse;
import com.turkcell.socceronlinemanagement.service.transfer.TransferPlayerRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teams")
@PreAuthorize("hasRole('ADMIN')")
public class TeamController {
    private final TeamService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TeamResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public TeamResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponse add(@Valid @RequestBody TeamRequest request) {
        return service.add(request);
    }

    @PostMapping("/transfer/player")
    public TeamResponse transferPlayer(@Valid @RequestBody TransferPlayerRequest request) {
        return service.addTransferPlayer( request);
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
