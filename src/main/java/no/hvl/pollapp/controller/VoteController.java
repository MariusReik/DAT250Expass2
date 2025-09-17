package no.hvl.pollapp.controller;

import no.hvl.pollapp.domain.Vote;
import no.hvl.pollapp.service.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/votes")
@CrossOrigin(origins = "http://localhost:5173")public class VoteController {

    @Autowired
    private PollManager pollManager;

    @GetMapping
    public List<Vote> getAllVotes() {
        return pollManager.getAllVotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVote(@PathVariable Long id) {
        Vote vote = pollManager.getVote(id);
        return vote == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(vote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long id) {
        if (pollManager.getVote(id) == null) {
            return ResponseEntity.notFound().build();
        }
        pollManager.deleteVote(id);
        return ResponseEntity.noContent().build();
    }
}