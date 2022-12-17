package com.binder.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reputation")
public class Reputation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_target")
    private Long review_target;

    @Column(name = "recipient")
    private Long recipient;

    @Column(name = "reviewer")
    private Long reviewer;

    @Column(name = "score")
    private int score;

    public Reputation() {

    }

    public Reputation(Long id, Long review_target, Long recipient, Long reviewer, int score) {
        this.id = id;
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

    public Long getReview_target() {
        return review_target;
    }

    public void setReview_target(Long review_target) {
        this.review_target = review_target;
    }

    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    public Long getReviewer() {
        return reviewer;
    }

    public void setReviewer(Long reviewer) {
        this.reviewer = reviewer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}