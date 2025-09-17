package no.hvl.dat250.jpa.polls;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class VoteOption {

    @Id
    @GeneratedValue
    private Long id;

    private String caption;
    private int presentationOrder;

    @ManyToOne
    private Poll poll;

    @OneToMany(mappedBy = "votesOn", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vote> votes = new LinkedHashSet<>();

    public VoteOption() {}

    public VoteOption(String caption, int presentationOrder, Poll poll) {
        this.caption = caption;
        this.presentationOrder = presentationOrder;
        this.poll = poll;
    }

    // getters
    public Long getId() { return id; }
    public String getCaption() { return caption; }
    public int getPresentationOrder() { return presentationOrder; }
    public Poll getPoll() { return poll; }
    public Set<Vote> getVotes() { return votes; }
}
