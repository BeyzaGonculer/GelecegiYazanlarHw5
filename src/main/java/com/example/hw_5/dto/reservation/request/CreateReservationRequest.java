package com.example.hw_5.dto.reservation.request;

import com.example.hw_5.entity.Book;
import com.example.hw_5.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class CreateReservationRequest {

    private boolean status;
    private Date creationDate;
    private Date expireDate;
    private int bookId;
    private int memberId;

    public CreateReservationRequest(boolean status, Date creationDate, Date expireDate, int bookId, int memberId) {
        this.status = status;
        this.creationDate = creationDate;
        this.expireDate = expireDate;
        this.bookId = bookId;
        this.memberId = memberId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
