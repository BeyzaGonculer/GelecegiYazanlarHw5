package com.example.hw_5.service;

import com.example.hw_5.dto.borrow.request.BorrowRequest;
import com.example.hw_5.dto.borrow.response.BorrowedResponse;
import com.example.hw_5.dto.fine.request.CreateFineRequest;
import com.example.hw_5.dto.fine.response.CreateFineResponse;
import com.example.hw_5.entity.*;
import com.example.hw_5.mapper.BorrowMapper;
import com.example.hw_5.mapper.FineMapper;
import com.example.hw_5.repository.BorrowRepository;
import com.example.hw_5.repository.FineRepository;
import com.example.hw_5.rules.BorrowBusinessRules;
import com.example.hw_5.rules.FineBusinessRules;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
@Validated
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowMapper borrowMapper;
    private final BorrowBusinessRules borrowBusinessRules;


    public BorrowService(BorrowRepository borrowRepository, BorrowMapper borrowMapper, BorrowBusinessRules borrowBusinessRules) {
        this.borrowRepository = borrowRepository;
        this.borrowMapper = borrowMapper;
        this.borrowBusinessRules = borrowBusinessRules;
    }

   public BorrowedResponse createBorrow(BorrowRequest request){

        Borrow borrow = borrowMapper.borrowRequestToBorrow(request);
        Member member = borrowBusinessRules.findMemberById(request.getMemberId());
        Book book = borrowBusinessRules.findBookByIsbn(request.getIsbnNumber());
        Personel personel = borrowBusinessRules.findPersonelById(request.getPersonelId());
        //book = borrow.getBook();
        BookCopy copy = borrowBusinessRules.findAvailableCopy(book);
        borrow.getBook().getIsbnNumber();

       //borrowBusinessRules.checkAvailableBook(book);

       borrowBusinessRules.checkMemberHasNoUnpaidFines(member);
       borrowBusinessRules.checkMemberNotBorrowSameBook(member, book);

       borrow.setBookCopy(copy);
       borrow.setBook(book);
       borrow.setMember(member);
       borrow.setPersonel(personel);
       book.setBookId(book.getBookId());

       copy.setAvailable(false);

       book.setAvailableCopies(book.getAvailableCopies() - 1);

       Date startDate = new Date();
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(startDate);
       borrow.setStartDate(startDate);

        if(member.getMembershipLevel().toUpperCase().equals("STANDARD")){
            calendar.add(Calendar.DAY_OF_MONTH, 14);
            borrow.setDueDate(calendar.getTime());
        }

        else if(member.getMembershipLevel().toUpperCase().equals("GOLD")){
            calendar.add(Calendar.DAY_OF_MONTH, 21);
            borrow.setDueDate(calendar.getTime());
       }




        Borrow savedBorrow = borrowRepository.save(borrow);
        return borrowMapper.borrowToBorrowResponse(savedBorrow);
   }
}
