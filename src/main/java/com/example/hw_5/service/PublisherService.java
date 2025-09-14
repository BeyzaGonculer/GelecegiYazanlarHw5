package com.example.hw_5.service;

import com.example.hw_5.entity.Category;
import com.example.hw_5.entity.Publisher;
import com.example.hw_5.repository.BookRepository;
import com.example.hw_5.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherService {

    private PublisherRepository publisherRepository;



    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Optional<Publisher> findPublisherById(int id){
        return  publisherRepository.findById(id);
    }
}
