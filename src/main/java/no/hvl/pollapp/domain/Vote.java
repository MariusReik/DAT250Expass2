package no.hvl.pollapp.domain;

import java.time.Instant;

public class Vote {
    private Instant publishedAt;
    private String username;
    private Long voteOptionId;

    public Vote() {
        this.publishedAt = Instant.now();
    }

    public Vote(String username, Long voteOptionId) {
        this();
        this.username = username;
        this.voteOptionId = voteOptionId;
    }

    // Getters and Setters
    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getVoteOptionId() {
        return voteOptionId;
    }

    public void setVoteOptionId(Long voteOptionId) {
        this.voteOptionId = voteOptionId;
    }
}