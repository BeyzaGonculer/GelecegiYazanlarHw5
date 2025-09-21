package com.example.hw_5.dto.borrow.request;

import jakarta.validation.constraints.NotNull;

public class ReturnBorrowRequest {

    @NotNull
    private int borrowId;

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }
}
