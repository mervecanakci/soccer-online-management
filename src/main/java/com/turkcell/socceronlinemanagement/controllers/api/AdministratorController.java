package com.turkcell.socceronlinemanagement.controllers.api;

import com.turkcell.socceronlinemanagement.business.abstracts.AdministratorService;
import com.turkcell.socceronlinemanagement.business.dto.requests.AdministratorRequest;
import com.turkcell.socceronlinemanagement.business.dto.responses.AdministratorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/administrators")
public class AdministratorController {

    private final AdministratorService service;

    @GetMapping
    public List<AdministratorResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public AdministratorResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AdministratorResponse add(@Valid @RequestBody AdministratorRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public AdministratorResponse update(@PathVariable int id, @RequestBody AdministratorRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}


