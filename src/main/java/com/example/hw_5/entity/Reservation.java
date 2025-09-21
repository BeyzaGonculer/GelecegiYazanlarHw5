package com.example.hw_5.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @Column(name="reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @Column(name="status")
    private boolean status;

    @Column(name="creation_date")
    private Date creationDate;

    @Column(name="expire_date")
    private Date expireDate;

    @ManyToOne() //FK  hangi tabloda ise Ona ManyToOne diğerine OneToMany
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne() //FK  hangi tabloda ise Ona ManyToOne diğerine OneToMany
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
