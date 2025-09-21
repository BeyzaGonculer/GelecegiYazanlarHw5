package com.example.hw_5.mapper;

import com.example.hw_5.dto.borrow.request.BorrowRequest;
import com.example.hw_5.dto.borrow.response.BorrowedResponse;
import com.example.hw_5.dto.fine.response.CreateFineResponse;
import com.example.hw_5.entity.Borrow;
import com.example.hw_5.entity.Fine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.xml.stream.events.StartDocument;

@Mapper(componentModel = "spring")
public interface BorrowMapper {

    @Mapping(source = "isbnNumber", target = "book.isbnNumber")
    @Mapping(source = "memberId", target = "member.memberId")
    @Mapping(source = "personelId", target = "personel.personelId")
    Borrow borrowRequestToBorrow(BorrowRequest borrowRequest);


    @Mapping(source = "personel.personelId", target = "personelId")
    @Mapping(source = "member.memberId", target = "memberId")
    @Mapping(source = "book.isbnNumber", target = "isbnNumber")
    BorrowedResponse borrowToBorrowResponse(Borrow borrow);



}
