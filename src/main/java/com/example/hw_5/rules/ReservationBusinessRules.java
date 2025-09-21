package com.example.hw_5.rules;

import com.example.hw_5.core.exception.ReservationException;
import com.example.hw_5.entity.Book;
import com.example.hw_5.entity.Member;
import com.example.hw_5.entity.Reservation;
import com.example.hw_5.repository.BookRepository;
import com.example.hw_5.repository.MemberRepository;
import com.example.hw_5.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationBusinessRules {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public ReservationBusinessRules(ReservationRepository reservationRepository, BookRepository bookRepository,
                                    MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public void availableCountMustBeZero(int bookId)
    {
        Book book = bookRepository.findById(bookId).stream().findFirst().orElseThrow(() -> new RuntimeException("Book not found"));
        if(book.getAvailableCopies() != 0){
            throw new ReservationException("Available copies count must be 0 !");
        }
    }

    public void hasActiveReservation(int userId, int bookId) {
        List<Reservation> reservation = reservationRepository.findByMember_MemberIdAndBook_BookId(userId,bookId);
        if (reservation != null && reservation.stream().anyMatch(r -> r.isStatus())) {
            throw new IllegalStateException("Aynı kitap için zaten aktif bir rezervasyonunuz var!");
        }
    }

    public Member getMember(int memberId)
    {
        return memberRepository.findById(memberId).stream().findFirst().orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public Book getBook(int bookId){
        return bookRepository.findById(bookId).stream().findFirst().orElseThrow(() -> new RuntimeException("Book not found"));
    }

}
