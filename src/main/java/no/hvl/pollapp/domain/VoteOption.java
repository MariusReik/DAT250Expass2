package no.hvl.pollapp.domain;

public class VoteOption {
    private String caption;
    private int presentationOrder;
    private Long pollId;

    public VoteOption() {}

    public VoteOption(String caption, int presentationOrder) {
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

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }
}