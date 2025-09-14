package com.example.hw_5.repository;

import com.example.hw_5.entity.Book;
import com.example.hw_5.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
