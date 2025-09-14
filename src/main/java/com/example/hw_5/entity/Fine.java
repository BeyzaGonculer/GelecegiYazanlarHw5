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

    @OneToOne
    @JoinColumn(name = "borrow_id", referencedColumnName = "borrow_id", nullable = false)
    private Borrow borrow;

    public int getFineId() {
        return fineId;
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

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }
}
