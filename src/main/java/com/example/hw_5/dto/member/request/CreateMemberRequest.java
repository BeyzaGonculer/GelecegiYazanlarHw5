package com.example.hw_5.dto.member.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateMemberRequest {
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 50)
    private String lastName;

    @Email
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone number must be 10-15 digits")
    //^ → stringin başını temsil eder.
    //[0-9] → sadece rakam kabul edilir.
    //{10,15} → minimum 10, maksimum 15 karakter uzunluğunda olmalı.
    //$ → stringin sonunu temsil eder.
    private String phoneNumber;

    @NotBlank(message = "Membership level cannot be empty")
    private String membershipLevel;

    private String status ;

    private boolean isPaid = false;

    // Getters & Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
