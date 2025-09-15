package com.example.hw_5.dto.book.request;

import jakarta.validation.constraints.*;

public class CreateBookRequest {

    @NotBlank(message = "Book name cannot be blank")
    @Size(min=2,max = 255)
    private String name;

    @Positive(message = "Page count must be positive")
    private int pageCount;

    @NotBlank(message = "ISBN cannot be blank")
    @Size(min = 8, max = 8, message = "ISBN must be exactly 8 characters")
    private String isbnNumber;

    @NotBlank(message = "Status is required (ACTIVE or INACTIVE)")
    private String status;

    @Min(value = 0, message = "Total copies must be 0 or greater")
    private int totalCopies;

    @Min(value = 0, message = "Available copies must be 0 or greater")
    private int availableCopies;

    @NotNull(message = "Author id is required")
    private int authorId;

    @NotNull(message = "Category id is required")
    private int categoryId;

    @NotNull(message = "Publisher  id is required")
    private int publisherId;

    // GETTERS AND SETTERS

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
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
