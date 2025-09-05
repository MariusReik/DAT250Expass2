package no.hvl.pollapp.service;

import no.hvl.pollapp.domain.*;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class PollManager {
    private Map<String, User> users = new HashMap<>();
    private Map<Long, Poll> polls = new HashMap<>();
    private Map<Long, Vote> votes = new HashMap<>();

    private Long nextId = 1L;

    // User operations
    public User addUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void deleteUser(String username) {
        users.remove(username);
    }

    // Poll operations
    public Poll addPoll(Poll poll) {
        poll.setId(nextId++);
        polls.put(poll.getId(), poll);
        return poll;
    }

    public Poll getPoll(Long id) {
        return polls.get(id);
    }

    public List<Poll> getAllPolls() {
        return new ArrayList<>(polls.values());
    }

    public void deletePoll(Long id) {
        polls.remove(id);
    }

    // Vote operations
    public Vote addVote(Vote vote) {
        vote.setId(nextId++);
        votes.put(vote.getId(), vote);
        return vote;
    }

    public Vote getVote(Long id) {
        return votes.get(id);
    }

    public List<Vote> getAllVotes() {
        return new ArrayList<>(votes.values());
    }

    public void deleteVote(Long id) {
        votes.remove(id);
    }
}