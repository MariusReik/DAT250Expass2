package no.hvl.pollapp.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Poll {
    private String question;
    private Instant publishedAt;
    private Instant validUntil;
    private String creatorUsername;
    private List<VoteOption> voteOptions = new ArrayList<>();

    public Poll() {
        this.publishedAt = Instant.now();
    }

    public Poll(String question, Instant validUntil, String creatorUsername) {
        this();
        this.question = question;
        this.validUntil = validUntil;
        this.creatorUsername = creatorUsername;
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

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public List<VoteOption> getVoteOptions() {
        return voteOptions;
    }

    public void setVoteOptions(List<VoteOption> voteOptions) {
        this.voteOptions = voteOptions;
    }
}