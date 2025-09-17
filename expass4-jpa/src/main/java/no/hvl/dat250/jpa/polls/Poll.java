package no.hvl.dat250.jpa.polls;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Poll {

    @Id
    @GeneratedValue
    private Long id;

    private String question;

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VoteOption> options = new ArrayList<>();

    public Poll() {}

    public Poll(String question, User createdBy) {
        this.question = question;
        this.createdBy = createdBy;
    }

    /**
     * Adds a new option to this Poll and returns it.
     */
    public VoteOption addVoteOption(String caption) {
        int order = this.options.size();
        VoteOption option = new VoteOption(caption, order, this);
        this.options.add(option);
        return option;
    }

    // getters
    public Long getId() { return id; }
    public String getQuestion() { return question; }
    public User getCreatedBy() { return createdBy; }
    public List<VoteOption> getOptions() { return options; }
}
