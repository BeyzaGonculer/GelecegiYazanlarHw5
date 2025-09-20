package com.example.hw_5.dto.fine.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateFineRequest {

    @Positive(message = "Fine amount must be positive")
    private double fineAmount;

    @NotNull(message = "Status is required")
    private Boolean status;



    // CONSTRUCTOR AND GETTER SETTERS

    public CreateFineRequest(boolean status, double fineAmount) {
        this.status = status;
        this.fineAmount = fineAmount;
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
