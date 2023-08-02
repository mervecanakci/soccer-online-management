package com.turkcell.socceronlinemanagement.api;

import com.turkcell.socceronlinemanagement.service.user.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @GetMapping
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Welcome Dashboard");
    }
    @PostMapping("{id}")
    public UserResponse add() {
        return null;
    }

}
