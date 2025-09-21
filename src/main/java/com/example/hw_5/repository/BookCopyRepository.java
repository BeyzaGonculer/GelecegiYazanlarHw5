package com.example.hw_5.repository;

import com.example.hw_5.entity.Book;
import com.example.hw_5.entity.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyRepository extends JpaRepository<BookCopy, Integer> {
}
