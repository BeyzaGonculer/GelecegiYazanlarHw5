package com.example.hw_5.service;

import com.example.hw_5.entity.Author;
import com.example.hw_5.entity.Category;
import com.example.hw_5.repository.AuthorRepository;
import com.example.hw_5.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;



    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> findAuthorById(int id){
        return  authorRepository.findById(id);
    }
}
