package com.example.hw_5.service;

import com.example.hw_5.dto.reservation.request.CreateReservationRequest;
import com.example.hw_5.dto.reservation.response.ReservationResponse;
import com.example.hw_5.entity.Book;
import com.example.hw_5.entity.Member;
import com.example.hw_5.entity.Reservation;
import com.example.hw_5.mapper.BookMapper;
import com.example.hw_5.mapper.ReservationMapper;
import com.example.hw_5.repository.ReservationRepository;
import com.example.hw_5.rules.ReservationBusinessRules;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Validated
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationBusinessRules reservationBusinessRules;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, ReservationBusinessRules reservationBusinessRules, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationBusinessRules = reservationBusinessRules;
        this.reservationMapper = reservationMapper;
    }

    public ReservationResponse createReservation(@Valid CreateReservationRequest createReservationRequest)
    {
        reservationBusinessRules.availableCountMustBeZero(createReservationRequest.getBookId());
        reservationBusinessRules.hasActiveReservation(createReservationRequest.getMemberId(), createReservationRequest.getBookId());

        Reservation reservation = reservationMapper.createReservationRequestToReservation(createReservationRequest);
        reservation.setExpireDate(null);

        Member member = reservationBusinessRules.getMember(createReservationRequest.getMemberId());
        Book book = reservationBusinessRules.getBook(createReservationRequest.getBookId());

        reservation.setBook(book);
        reservation.setMember(member);

        reservation = reservationRepository.save(reservation);

        return reservationMapper.reservationToCreateReservationRequest(reservation);
    }

    public ReservationResponse cancelReservation(int reservationId)
    {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("Reservsyon bulunamadı"));;

        reservation.setStatus(false);
        reservationRepository.save(reservation);
        return reservationMapper.reservationToCreateReservationRequest(reservation);
    }

    public List<ReservationResponse> checkReservationStatus(){
        Date now = new Date();

        List<Reservation> allReservations = reservationRepository.findAll();
        List<ReservationResponse> unActiveReservations = new ArrayList<>();
        if(allReservations != null)
        {
            for(Reservation item : allReservations)
            {
                if(item.getExpireDate().before(now)) {
                    item.setStatus(false);
                    reservationRepository.save(item);
                    unActiveReservations.add(reservationMapper.reservationToCreateReservationRequest(item));
                }
            }
        }
        return unActiveReservations;
    }
}
