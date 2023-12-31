package com.turkcell.socceronlinemanagement.api;

import com.turkcell.socceronlinemanagement.service.user.UserAuthRequest;
import com.turkcell.socceronlinemanagement.service.user.UserRegisterRequest;

import com.turkcell.socceronlinemanagement.service.user.UserResponse;
import com.turkcell.socceronlinemanagement.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody UserRegisterRequest request) throws InterruptedException{
        Thread.sleep(2000);
        return service.register(request);
    }

    @PostMapping("/authenticate")
    public UserResponse authenticate(@RequestBody UserAuthRequest request) {
        return service.authenticate(request);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAll() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserResponse getById(@PathVariable int id) {
        return service.getById(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse add(@Valid @RequestBody UserAuthRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse update(@PathVariable int id, @RequestBody UserAuthRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }


}
