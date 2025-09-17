package no.hvl.dat250.jpa.polls;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users") // NB: må hete "users" fordi testen spør mot denne tabellen
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Poll> created = new LinkedHashSet<>();

    @OneToMany(mappedBy = "votedBy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vote> votes = new LinkedHashSet<>();

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.created = new LinkedHashSet<>();
    }

    /**
     * Creates a new Poll object for this user with the given poll question and returns it.
     */
    public Poll createPoll(String question) {
        Poll poll = new Poll(question, this);
        this.created.add(poll);
        return poll;
    }

    /**
     * Creates a new Vote for a given VoteOption in a Poll and returns it.
     */
    public Vote voteFor(VoteOption option) {
        Vote vote = new Vote(this, option);
        this.votes.add(vote);
        option.getVotes().add(vote);
        return vote;
    }

    // getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public Set<Poll> getCreated() { return created; }
    public Set<Vote> getVotes() { return votes; }
}
