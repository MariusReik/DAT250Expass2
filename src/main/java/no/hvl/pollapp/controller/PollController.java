package no.hvl.pollapp.controller;

import no.hvl.pollapp.domain.Poll;
import no.hvl.pollapp.domain.Vote;
import no.hvl.pollapp.service.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/polls")
@CrossOrigin(origins = "http://localhost:5173")
public class PollController {

    @Autowired
    private PollManager pollManager;

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll created = pollManager.addPoll(poll);
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
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{pollId}/votes")
    public List<Vote> getVotesForPoll(@PathVariable Long pollId) {
        return pollManager.getVotesForPoll(pollId);
    }

    @Autowired
    private PollEventPublisher eventPublisher;

    eventPublisher.publishVoteEvent(poll.getName(), "Poll created");

    eventPublisher.publishVoteEvent(poll.getName(), choice);

}