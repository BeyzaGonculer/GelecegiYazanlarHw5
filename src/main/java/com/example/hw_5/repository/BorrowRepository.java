package com.example.hw_5.repository;

import com.example.hw_5.entity.Book;
import com.example.hw_5.entity.Borrow;
import com.example.hw_5.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow,Integer> {

    boolean existsByMemberAndBookAndDeliveryDateIsNull(Member member, Book book);

}
