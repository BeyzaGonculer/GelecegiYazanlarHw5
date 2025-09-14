package com.example.hw_5.dto.book.response;

public class GetBookByIdResponse {

    private int bookId;
    private String authorName;
    private String categoryName;
    private int pageCount;
    private String publisherName;
    private String isbnNumber;
    private String name;

    public GetBookByIdResponse(int bookId, String authorName, String categoryName, int pageCount, String publisherName, String isbnNumber, String name) {
        this.bookId = bookId;
        this.authorName = authorName;
        this.categoryName = categoryName;
        this.pageCount = pageCount;
        this.publisherName = publisherName;
        this.isbnNumber = isbnNumber;
        this.name = name;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
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
