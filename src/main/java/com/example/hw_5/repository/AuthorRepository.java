package com.example.hw_5.repository;

import com.example.hw_5.entity.Author;
import com.example.hw_5.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
