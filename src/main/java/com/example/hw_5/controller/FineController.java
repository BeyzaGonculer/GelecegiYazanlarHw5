package com.example.hw_5.controller;

import com.example.hw_5.dto.fine.request.CreateFineRequest;
import com.example.hw_5.dto.fine.response.CreateFineResponse;
import com.example.hw_5.dto.fine.response.GetFinesResponse;
import com.example.hw_5.service.FineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fines")
public class FineController
{
    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @PostMapping("/{id}/pay")
    public CreateFineResponse postFine(
            @PathVariable int id,
            @RequestBody CreateFineRequest request) {
        return fineService.createFine(id, request);
    }
    @GetMapping("/{memberId}")
    public List<GetFinesResponse> getFines(
            @PathVariable int memberId) {
        return fineService.getFines(memberId);
    }
}
