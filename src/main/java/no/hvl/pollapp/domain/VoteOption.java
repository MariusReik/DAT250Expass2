package no.hvl.pollapp.domain;

import java.util.ArrayList;
import java.util.List;

public class voteOption {
    private String caption;
    private int presentationOrder;
    private Poll poll;
    private List<Vote> votes;

    public VoteOption() {
        this.votes = new ArrayList<>();
    }

    public VoteOption(String caption, int presentationOrder) {
        this();
        this.caption = caption;
        this.presentationOrder = presentationOrder;
    }

    // Getters and Setters
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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
