package com.example.hw_5.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private int bookId;

    @Column(name="name")
    private String name;

    @Column(name="page_count")
    private int pageCount;

    @Column(name="isbn_number")
    private String isbnNumber;

    @Column(name="status")
    private String status;

    @Column(name="price")
    private int price;

    @Column(name="total_copies")
    private int totalCopies;

    @Column(name="available_copies")
    private int availableCopies;

    @ManyToOne() //FK  hangi tabloda ise Ona ManyToOne diğerine OneToMany
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne() //FK  hangi tabloda ise Ona ManyToOne diğerine OneToMany
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToOne() //FK  hangi tabloda ise Ona ManyToOne diğerine OneToMany
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Book sadece Borrow tarafından sahiplenilir
    @JsonIgnore
    @OneToOne(mappedBy = "book")
    private Borrow borrow;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Reservation> reservation;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
