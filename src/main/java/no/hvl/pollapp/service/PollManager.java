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

    // Users
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
        List<Long> pollIds = polls.values().stream()
                .filter(p -> username.equals(p.getCreatorUsername()))
                .map(Poll::getId)
                .collect(Collectors.toList());
        for (Long pid : pollIds) deletePoll(pid);
        votes.values().removeIf(v -> username.equals(v.getUsername()));
        users.remove(username);
    }

    // Polls
    public Poll addPoll(Poll poll) {
        poll.setId(nextId++);
        if (poll.getVoteOptions() != null) {
            for (VoteOption o : poll.getVoteOptions()) {
                o.setId(nextId++);
                o.setPollId(poll.getId());
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
        votes.values().removeIf(v -> v.getPollId().equals(id));
        polls.remove(id);
    }

    public boolean isValidOption(Long pollId, Long optionId) {
        Poll p = polls.get(pollId);
        if (p == null || p.getVoteOptions() == null) return false;
        return p.getVoteOptions().stream()
                .anyMatch(o -> Objects.equals(o.getId(), optionId));
    }

    // Votes
    public Vote addOrUpdateVote(Vote vote, boolean createIfMissing) {
        Vote existing = findVoteByUserAndPoll(vote.getUsername(), vote.getPollId());
        if (existing != null) {
            existing.setVoteOptionId(vote.getVoteOptionId());
            return existing;
        }
        if (!createIfMissing) return null;
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

    public List<Vote> getVotesForPoll(Long pollId) {
        List<Vote> out = new ArrayList<>();
        for (Vote v : votes.values()) if (v.getPollId().equals(pollId)) out.add(v);
        return out;
    }

    public Vote findVoteByUserAndPoll(String username, Long pollId) {
        for (Vote v : votes.values()) {
            if (Objects.equals(v.getPollId(), pollId) && Objects.equals(v.getUsername(), username)) {
                return v;
            }
        }
        return null;
    }

    public void deleteVote(Long id) {
        votes.remove(id);
    }
}
