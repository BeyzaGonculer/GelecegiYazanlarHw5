package com.example.hw_5.dto.book.response;

public class GetBookParamsResponse {
    private int bookId;
    private int authorId;
    private int categoryId;
    private int pageCount;
    private int publisherId;
    private String isbnNumber;
    private String name;
    private String status;
    private int totalCopies;

    public String getStatus() {
        return status;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GetBookParamsResponse(int bookId, int authorId, int categoryId, int pageCount, int publisherId,
                                 String isbnNumber, String name, String status, int totalCopies) {
        this.bookId = bookId;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.pageCount = pageCount;
        this.publisherId = publisherId;
        this.isbnNumber = isbnNumber;
        this.name = name;
        this.status = status;
        this.totalCopies = totalCopies;
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
