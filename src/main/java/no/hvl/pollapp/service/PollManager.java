package no.hvl.pollapp.service;

import no.hvl.pollapp.domain.*;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class PollManager {
    private Map<String, User> users;
    private Map<String, Poll> polls;
    private Map<String, List<Vote>> pollVotes;

    public PollManager() {
        this.users = new HashMap<>();
        this.polls = new HashMap<>();
        this.pollVotes = new HashMap<>();
    }

    // User methods
    public void addUser(String username, User user) {
        users.put(username, user);
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public Map<String, User> getUsers() {
        return users;
    }

    // Poll methods
    public void addPoll(String pollId, Poll poll) {
        polls.put(pollId, poll);
        pollVotes.put(pollId, new ArrayList<>());
    }

    public Poll getPoll(String pollId) {
        return polls.get(pollId);
    }

    public Map<String, Poll> getPolls() {
        return polls;
    }

    public void deletePoll(String pollId) {
        polls.remove(pollId);
        pollVotes.remove(pollId);
    }

    // Vote methods
    public List<Vote> getVotesForPoll(String pollId) {
        return pollVotes.get(pollId);
    }

    public void addVote(String pollId, Vote vote) {
        pollVotes.get(pollId).add(vote);
    }

    public void updateVote(String pollId, Vote vote) {
        List<Vote> votes = pollVotes.get(pollId);
        votes.removeIf(v -> v.getUsername().equals(vote.getUsername()));
        votes.add(vote);
    }

    public List<Vote> getAllVotes() {
        List<Vote> allVotes = new ArrayList<>();
        for (List<Vote> pollVoteList : pollVotes.values()) {
            allVotes.addAll(pollVoteList);
        }
        return allVotes;
    }
}