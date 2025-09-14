package com.example.hw_5.service;

import com.example.hw_5.entity.Category;
import com.example.hw_5.repository.BookRepository;
import com.example.hw_5.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;



    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    public Optional<Category> findCategoryById(int id){
        return  categoryRepository.findById(id);
    }
}
