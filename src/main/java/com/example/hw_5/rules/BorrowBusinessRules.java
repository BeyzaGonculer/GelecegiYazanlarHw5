package com.example.hw_5.rules;

import com.example.hw_5.entity.Book;
import com.example.hw_5.entity.BookCopy;
import com.example.hw_5.entity.Member;
import com.example.hw_5.entity.Personel;
import com.example.hw_5.repository.*;
import org.springframework.stereotype.Component;

@Component
public class BorrowBusinessRules {

    private final MemberRepository memberRepository;
    private final FineRepository fineRepository;
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final PersonelRepository personelRepository;

    public BorrowBusinessRules(MemberRepository memberRepository, FineRepository fineRepository, BorrowRepository borrowRepository, BookRepository bookRepository, PersonelRepository personelRepository) {
        this.memberRepository = memberRepository;
        this.fineRepository = fineRepository;
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.personelRepository = personelRepository;
    }

    public Member findMemberById(int memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        return member;
    }

    public void checkAvailableBook(Book book){
        if(book.getAvailableCopies() <= 0){
            throw new RuntimeException("Kitap stoğu yok.");
        }
    }

    public BookCopy findAvailableCopy(Book book) {
        return book.getCopies().stream()
                .filter(BookCopy::isAvailable)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Bu kitap için uygun kopya yok."));
    }


    public Book findBookByIsbn(String isbn){
        return bookRepository.findByIsbnNumber(isbn)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));
    }

    public Personel findPersonelById(int personelId){
        return personelRepository.findById(personelId)
                .orElseThrow(() -> new RuntimeException("Personel bulunamadı"));
    }

    public void checkMemberHasNoUnpaidFines(Member member){
        boolean hasUnpaid = memberRepository.existsByMemberIdAndIsPaidFalse(member.getMemberId());
        if(hasUnpaid){
            throw new RuntimeException("Üyenin ödenmemiş cezası var.");
        }
    }

    public void checkMemberNotBorrowSameBook(Member member, Book book){
        boolean alreadyBorrowed = borrowRepository.existsByMemberAndBookAndDeliveryDateIsNull(member, book);
        if(alreadyBorrowed){
            throw new RuntimeException("Üye aynı kitabı iade etmeden tekrar alamaz.");
        }
    }





}
