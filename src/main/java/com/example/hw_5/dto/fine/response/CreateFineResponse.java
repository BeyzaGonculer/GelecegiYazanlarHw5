package com.example.hw_5.dto.fine.response;

public class CreateFineResponse {

    private int fineId;
    private boolean status;
    private double fineAmount;
    private int memberId;

    public CreateFineResponse(int fineId, boolean status, double fineAmount, int memberId) {
        this.fineId = fineId;
        this.status = status;
        this.fineAmount = fineAmount;
        this.memberId = memberId;
    }

    public int getFineId() {
        return fineId;
    }

    public void setFineId(int fineId) {
        this.fineId = fineId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
