package com.binder.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reputation")
public class Reputation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_target")
    private int review_target;

    @Column(name = "recipient")
    private int recipient;

    @Column(name = "reviewer")
    private int reviewer;

    @Column(name = "score")
    private int score;

    public Reputation() {

    }

    public Reputation(int review_target,int recipient, int reviewer, int score) {
        this.review_target = review_target;
        this.recipient = recipient;
        this.reviewer = reviewer;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getReview_target() {
        return review_target;
    }

    public void setReview_target(int review_target) {
        this.review_target = review_target;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public int getReviewer() {
        return reviewer;
    }

    public void setReviewer(int reviewer) {
        this.reviewer = reviewer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
