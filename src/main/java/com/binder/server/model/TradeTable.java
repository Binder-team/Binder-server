package com.binder.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trade_table")
public class TradeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender")
    private Long sender;

    @Column(name = "receiver")
    private Long receiver;

    @Column(name = "book_id")
    private Long book_id;

    @Column(name = "isMatched")
    private boolean isMatched;

    @Column(name = "isAccepted")
    private boolean isAccepted;
    @Column(name = "isExchanged")
    private boolean isExchanged;

    public TradeTable() {
    }

    public TradeTable(Long id, Long sender, Long receiver, Long book_id, boolean isMatched, boolean isAccepted, boolean isExchanged) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.book_id = book_id;
        this.isMatched = isMatched;
        this.isAccepted = isAccepted;
        this.isExchanged = isExchanged;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public boolean isIsMatched() {
        return isMatched;
    }

    public void setIsMatched(boolean is_matched) {
        this.isMatched = is_matched;
    }

    public boolean isIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public boolean isIsExchanged() {
        return isExchanged;
    }

    public void setIsExchanged(boolean is_exchanged) {
        this.isExchanged = is_exchanged;
    }
}
