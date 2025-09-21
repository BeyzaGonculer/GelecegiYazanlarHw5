package com.example.hw_5.dto.book.response;

import java.util.List;

public class CreatedBookResponse {

    private int bookId;
    private String name;
    private int pageCount;
    private String isbnNumber;
    private int totalCopies;
    private int availableCopies;
    private List<Integer> copyIds;

    public CreatedBookResponse(int bookId, String publisherName, String categoryName, String authorName, int availableCopies, int totalCopies, String status, String isbnNumber, int pageCount, String name, List<Integer> copyIds) {
        this.bookId = bookId;
        this.publisherName = publisherName;
        this.categoryName = categoryName;
        this.authorName = authorName;
        this.availableCopies = availableCopies;
        this.totalCopies = totalCopies;
        this.isbnNumber = isbnNumber;
        this.pageCount = pageCount;
        this.name = name;
        this.copyIds = copyIds;
    }

    public List<Integer> getCopyIds() {
        return copyIds;
    }

    public void setCopyIds(List<Integer> copyIds) {
        this.copyIds = copyIds;
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

    private String authorName;
    private String categoryName;
    private String publisherName;





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
