package no.hvl.pollapp.controller;

import no.hvl.pollapp.domain.Poll;
import no.hvl.pollapp.domain.Vote;
import no.hvl.pollapp.service.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollManager pollManager;

    // Opprett poll
    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        if (poll.getCreatorUsername() == null || pollManager.getUser(poll.getCreatorUsername()) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Poll created = pollManager.addPoll(poll);
        return ResponseEntity.created(URI.create("/polls/" + created.getId())).body(created);
    }

    // List alle polls
    @GetMapping
    public List<Poll> getAllPolls() {
        return pollManager.getAllPolls();
    }

    // Hent én poll
    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        Poll p = pollManager.getPoll(id);
        return p == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
    }

    // Slett poll
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Long id) {
        if (pollManager.getPoll(id) == null) return ResponseEntity.notFound().build();
        pollManager.deletePoll(id);
        return ResponseEntity.noContent().build();
    }

    // Opprett stemme for poll
    @PostMapping("/{pollId}/votes")
    public ResponseEntity<Vote> createVoteForPoll(@PathVariable Long pollId, @RequestBody Vote vote) {
        if (pollManager.getPoll(pollId) == null) return ResponseEntity.notFound().build();
        if (vote.getUsername() == null || pollManager.getUser(vote.getUsername()) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!pollManager.isValidOption(pollId, vote.getVoteOptionId()))
            return ResponseEntity.badRequest().build();

        vote.setPollId(pollId);
        Vote created = pollManager.addOrUpdateVote(vote, true);
        return ResponseEntity.created(URI.create("/votes/" + created.getId())).body(created);
    }

    // Oppdater stemme for poll
    @PutMapping("/{pollId}/votes")
    public ResponseEntity<Vote> updateVoteForPoll(@PathVariable Long pollId, @RequestBody Vote vote) {
        if (pollManager.getPoll(pollId) == null) return ResponseEntity.notFound().build();
        if (vote.getUsername() == null || pollManager.getUser(vote.getUsername()) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (!pollManager.isValidOption(pollId, vote.getVoteOptionId()))
            return ResponseEntity.badRequest().build();

        vote.setPollId(pollId);
        Vote updated = pollManager.addOrUpdateVote(vote, false);
        return ResponseEntity.ok(updated);
    }

    // List stemmer for poll
    @GetMapping("/{pollId}/votes")
    public List<Vote> getVotesForPoll(@PathVariable Long pollId) {
        return pollManager.getVotesForPoll(pollId);
    }
}