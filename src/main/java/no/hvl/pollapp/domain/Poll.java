package no.hvl.pollapp.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Poll {
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private User creator;
    private List<VoteOption> voteOptions;

    public Poll() {
        this.voteOptions = new ArrayList<>();
    }

    public Poll(String question, Instant publishedAt, Instant validUntil) {
        this();
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
    }

    // Getters and Setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<VoteOption> getVoteOptions() {
        return voteOptions;
    }

    public void setVoteOptions(List<VoteOption> voteOptions) {
        this.voteOptions = voteOptions;
    }
}
