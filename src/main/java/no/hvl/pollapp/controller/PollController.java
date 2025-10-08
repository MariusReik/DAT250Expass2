package no.hvl.pollapp.controller;

import no.hvl.pollapp.domain.Poll;
import no.hvl.pollapp.domain.Vote;
import no.hvl.pollapp.service.PollManager;
import no.hvl.pollapp.service.PollEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polls")
@CrossOrigin(origins = "http://localhost:5173")
public class PollController {

    @Autowired
    private PollManager pollManager;

    @Autowired
    private PollEventPublisher eventPublisher;

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll created = pollManager.addPoll(poll);

        // Send event når et nytt poll opprettes
        eventPublisher.publishVoteEvent(created.getQuestion(), "Poll created");

        return ResponseEntity.ok(created);
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollManager.getAllPolls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        Poll poll = pollManager.getPoll(id);
        return poll == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(poll);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Long id) {
        if (pollManager.getPoll(id) == null) {
            return ResponseEntity.notFound().build();
        }
        pollManager.deletePoll(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{pollId}/votes")
    public ResponseEntity<Vote> voteOnPoll(@PathVariable Long pollId, @RequestBody Vote vote) {
        vote.setPollId(pollId);
        Vote result = pollManager.addOrUpdateVote(vote);

        // Send event når noen stemmer
        eventPublisher.publishVoteEvent(result.getPollId().toString(), "Vote registered");

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{pollId}/votes")
    public List<Vote> getVotesForPoll(@PathVariable Long pollId) {
        return pollManager.getVotesForPoll(pollId);
    }
}
