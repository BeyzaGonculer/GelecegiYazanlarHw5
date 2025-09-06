package com.example.hw_5.entity;


import jakarta.persistence.*;

@Entity()
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

    @ManyToOne() //FK  hangi tabloda ise Ona ManyToOne diğerine OneToMany
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne() //FK  hangi tabloda ise Ona ManyToOne diğerine OneToMany
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @ManyToOne() //FK  hangi tabloda ise Ona ManyToOne diğerine OneToMany
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToOne
    @JoinColumn(name = "borrow_id", nullable = false)
    private Borrow borrow;

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
