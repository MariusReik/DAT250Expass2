package no.hvl.pollapp.controller;

import no.hvl.pollapp.domain.User;
import no.hvl.pollapp.service.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PollManager pollManager;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().isBlank())
            return ResponseEntity.badRequest().body("Username cannot be blank");
        if (pollManager.getUser(user.getUsername()) != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists: " + user.getUsername());
        User created = pollManager.addUser(user);
        return ResponseEntity.created(URI.create("/users/" + created.getUsername())).body(created);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return pollManager.getAllUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username) {
        User u = pollManager.getUser(username);
        return u == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found") : ResponseEntity.ok(u);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        if (pollManager.getUser(username) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        pollManager.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}

