package com.binder.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trade_table")
public class TradeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender")
    private int sender;

    @Column(name = "receiver")
    private int receiver;

    @Column(name = "book_id")
    private int book_id;

    @Column(name = "is_matched")
    private boolean is_matched;

    @Column(name = "is_accepted")
    private boolean is_accepted;
    @Column(name = "is_exchanged")
    private boolean is_exchanged;

    public TradeTable() {
    }

    public TradeTable(Long id, int sender, int receiver, int book_id, boolean is_matched, boolean is_accepted, boolean is_exchanged) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.book_id = book_id;
        this.is_matched = is_matched;
        this.is_accepted = is_accepted;
        this.is_exchanged = is_exchanged;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public boolean isIs_matched() {
        return is_matched;
    }

    public void setIs_matched(boolean is_matched) {
        this.is_matched = is_matched;
    }

    public boolean isIs_accepted() {
        return is_accepted;
    }

    public void setIs_accepted(boolean is_accepted) {
        this.is_accepted = is_accepted;
    }

    public boolean isIs_exchanged() {
        return is_exchanged;
    }

    public void setIs_exchanged(boolean is_exchanged) {
        this.is_exchanged = is_exchanged;
    }
}
