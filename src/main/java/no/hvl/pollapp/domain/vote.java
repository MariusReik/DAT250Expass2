package no.hvl.pollapp.domain;

import java.time.Instant;

    public class vote {
        private Instant publishedAt;
        private User voter;
        private VoteOption voteOption;

        public vote() {
        }

        public vote(Instant publishedAt, User voter, VoteOption voteOption) {
            this.publishedAt = publishedAt;
            this.voter = voter;
            this.voteOption = voteOption;
        }

        // Getters and Setters
        public Instant getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(Instant publishedAt) {
            this.publishedAt = publishedAt;
        }

        public User getVoter() {
            return voter;
        }

        public void setVoter(User voter) {
            this.voter = voter;
        }

        public VoteOption getVoteOption() {
            return voteOption;
        }

        public void setVoteOption(VoteOption voteOption) {
            this.voteOption = voteOption;
        }
    }