package com.example.hw_5.dto.book.request;

public class UpdateBookRequest {

    private int bookId;
    private int authorId;
    private int categoryId;
    private int pageCount;
    private int publisherId;
    private String isbnNumber;
    private String name;

    public UpdateBookRequest(int bookId, int authorId, int categoryId, int pageCount, int publisherId, String isbnNumber, String name) {
        this.bookId = bookId;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.pageCount = pageCount;
        this.publisherId = publisherId;
        this.isbnNumber = isbnNumber;
        this.name = name;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
