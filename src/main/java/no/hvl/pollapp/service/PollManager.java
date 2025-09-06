package no.hvl.pollapp.service;

import no.hvl.pollapp.domain.*;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PollManager {
    private final Map<String, User> users = new HashMap<>();
    private final Map<Long, Poll> polls = new HashMap<>();
    private final Map<Long, Vote> votes = new HashMap<>();
    private long nextId = 1L;

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

    public Poll addPoll(Poll poll) {
        poll.setId(nextId++);
        if (poll.getVoteOptions() != null) {
            for (VoteOption option : poll.getVoteOptions()) {
                option.setId(nextId++);
                option.setPollId(poll.getId());
            }
        }
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
        votes.values().removeIf(vote -> vote.getPollId().equals(id));
        polls.remove(id);
    }

    public Vote addOrUpdateVote(Vote vote) {
        Vote existing = findVoteByUserAndPoll(vote.getUsername(), vote.getPollId());
        if (existing != null) {
            existing.setVoteOptionId(vote.getVoteOptionId());
            return existing;
        }
        vote.setId(nextId++);
        votes.put(vote.getId(), vote);
        return vote;
    }

    public Vote findVoteByUserAndPoll(String username, Long pollId) {
        for (Vote v : votes.values()) {
            if (v.getPollId().equals(pollId) && v.getUsername().equals(username)) {
                return v;
            }
        }
        return null;
    }

    public Vote getVote(Long id) {
        return votes.get(id);
    }

    public List<Vote> getAllVotes() {
        return new ArrayList<>(votes.values());
    }

    public List<Vote> getVotesForPoll(Long pollId) {
        return votes.values().stream()
                .filter(vote -> vote.getPollId().equals(pollId))
                .collect(Collectors.toList());
    }

    public void deleteVote(Long id) {
        votes.remove(id);
    }
}