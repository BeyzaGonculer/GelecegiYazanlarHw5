package com.example.hw_5.mapper;

import com.example.hw_5.dto.book.response.CreatedBookResponse;
import com.example.hw_5.dto.reservation.request.CreateReservationRequest;
import com.example.hw_5.dto.reservation.response.ReservationResponse;
import com.example.hw_5.entity.Book;
import com.example.hw_5.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "bookId", target = "book.bookId")
    @Mapping(source = "memberId", target = "member.memberId")
    Reservation createReservationRequestToReservation(CreateReservationRequest createReservationRequest);

    @Mapping(target = "bookName", source = "book.name")
    @Mapping(target = "memberFirstName", source = "member.firstName")
    ReservationResponse reservationToCreateReservationRequest(Reservation reservation);
}
