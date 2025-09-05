package no.hvl.pollapp.controller;

import no.hvl.pollapp.domain.Vote;
import no.hvl.pollapp.service.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private PollManager pollManager;

    @GetMapping
    public List<Vote> getAllVotes() {
        return pollManager.getAllVotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVote(@PathVariable Long id) {
        Vote v = pollManager.getVote(id);
        return v == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(v);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long id) {
        if (pollManager.getVote(id) == null) return ResponseEntity.notFound().build();
        pollManager.deleteVote(id);
        return ResponseEntity.noContent().build();
    }
}