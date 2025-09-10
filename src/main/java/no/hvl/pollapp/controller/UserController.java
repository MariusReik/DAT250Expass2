package no.hvl.pollapp.controller;

import no.hvl.pollapp.domain.User;
import no.hvl.pollapp.service.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private PollManager pollManager;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (pollManager.getUser(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User created = pollManager.addUser(user);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return pollManager.getAllUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = pollManager.getUser(username);
        return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        if (pollManager.getUser(username) == null) {
            return ResponseEntity.notFound().build();
        }
        pollManager.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}
