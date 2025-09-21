package com.example.hw_5.controller;

import com.example.hw_5.dto.borrow.request.BorrowRequest;
import com.example.hw_5.dto.borrow.request.ReturnBorrowRequest;
import com.example.hw_5.dto.borrow.response.BorrowedResponse;
import com.example.hw_5.dto.borrow.response.ReturnBorrowResponse;
import com.example.hw_5.service.BorrowService;
import com.example.hw_5.service.FineService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

   @PostMapping
    public BorrowedResponse createBorrow(BorrowRequest borrowRequest){

        return borrowService.createBorrow(borrowRequest);

    }

    @PostMapping("/return")
    public ReturnBorrowResponse returnBorrow(@RequestBody ReturnBorrowRequest request) {
        return borrowService.returnBorrow(request);
    }



}
