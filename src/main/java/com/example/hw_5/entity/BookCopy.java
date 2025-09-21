package com.example.hw_5.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "book_copy")
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="copy_id")
    private int copyId;


    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;  // Her kopya 1 kitaba bağlıdır

    @OneToMany(mappedBy = "bookCopy")
    private List<Borrow> borrows; // Aynı kopya defalarca ödünç alınabilir


    @Column(name="is_avaliable")
    private boolean isAvailable;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setCopyId(int copyId) {
        this.copyId = copyId;
    }



    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
