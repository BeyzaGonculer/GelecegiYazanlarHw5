package com.example.hw_5.controller;


import com.example.hw_5.dto.member.request.CreateMemberRequest;
import com.example.hw_5.dto.member.response.CreateMemberResponse;
import com.example.hw_5.dto.reservation.request.CreateReservationRequest;
import com.example.hw_5.dto.reservation.response.ReservationResponse;
import com.example.hw_5.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse reservationResponseMember(@Valid @RequestBody CreateReservationRequest request) {

        return reservationService.createReservation(request);
    }

    @PostMapping("/{id}/cancel")
    public ReservationResponse cancelReservation(@PathVariable int id)
    {
        return  reservationService.cancelReservation(id);
    }

    @PostMapping("/check")
    public List<ReservationResponse> checkReservationStatus()
    {
        return reservationService.checkReservationStatus();
    }
}
