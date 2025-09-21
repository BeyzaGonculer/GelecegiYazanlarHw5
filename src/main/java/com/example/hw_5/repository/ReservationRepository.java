package com.example.hw_5.repository;

import com.example.hw_5.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByMember_MemberIdAndBook_BookId(int memberId, int bookId);

}
