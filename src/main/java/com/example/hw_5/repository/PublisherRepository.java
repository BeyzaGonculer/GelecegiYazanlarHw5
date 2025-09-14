package com.example.hw_5.repository;

import com.example.hw_5.entity.Book;
import com.example.hw_5.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
