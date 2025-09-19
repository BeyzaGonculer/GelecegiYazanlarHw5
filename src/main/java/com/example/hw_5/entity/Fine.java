package com.example.hw_5.entity;

import jakarta.persistence.*;

@Entity
@Table(name="fine")
public class Fine {

    @Id
    @Column(name="fine_id")
    private int fineId;

    @Column(name="status")
    private boolean status;

    @Column(name="fine_amount")
    private double fineAmount;

    @ManyToOne()
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public int getFineId() {
        return fineId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

}
