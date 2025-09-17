package no.hvl.dat250.jpa.polls;

import jakarta.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User votedBy;

    @ManyToOne
    private VoteOption votesOn;

    public Vote() {}

    public Vote(User votedBy, VoteOption votesOn) {
        this.votedBy = votedBy;
        this.votesOn = votesOn;
    }

    // getters
    public Long getId() { return id; }
    public User getVotedBy() { return votedBy; }
    public VoteOption getVotesOn() { return votesOn; }
}
