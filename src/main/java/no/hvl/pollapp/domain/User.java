package no.hvl.pollapp.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String email;
    private List<Poll> createdPolls;
    private List<vote> votes;

    public User() {
        this.createdPolls = new ArrayList<>();
        this.votes = new ArrayList<>();
    }

    public User(String username, String email) {
        this();
        this.username = username;
        this.email = email;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
