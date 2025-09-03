package no.hvl.pollapp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;

public class VoteOption {
    private Long id;
    private String caption;
    private int presentationOrder;

    @JsonBackReference("poll-options")
    private Poll poll;

    @JsonManagedReference("option-votes")
    private List<vote> votes = new ArrayList<>();

    // Default constructor
    public VoteOption() {}

    // Constructor with parameters
    public VoteOption(String caption, int presentationOrder) {
        this.caption = caption;
        this.presentationOrder = presentationOrder;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public List<vote> getVotes() {
        return votes;
    }

    public void setVotes(List<vote> votes) {
        this.votes = votes;
    }

    // Helper method
    public void addVote(vote vote) {
        votes.add(vote);
        vote.setVoteOption(this);
    }
}