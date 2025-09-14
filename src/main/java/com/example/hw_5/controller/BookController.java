package com.example.hw_5.controller;

import com.example.hw_5.dto.BookForAddDto;
import com.example.hw_5.dto.book.request.CreateBookRequest;
import com.example.hw_5.dto.book.request.SearchBookRequest;
import com.example.hw_5.dto.book.request.UpdateBookRequest;
import com.example.hw_5.dto.book.response.*;
import com.example.hw_5.entity.Book;
import com.example.hw_5.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books") // localhost:port/api/v1/products ile başlıyorsa istek buraya gelsin.

public class BookController {
    private BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public List<GetAllBookResponse> getAll() {
        return bookService.getAll();
    }

    // Ekleme endpointleri ekleme sonrası durum için eklenen entity'i geri döner.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) // eğer işlem başarılı olursa, status code olarak şunu dön.
    public CreatedBookResponse add(@Valid @RequestBody CreateBookRequest request)
    {
        return bookService.add(request);
    }

    @GetMapping("{id}")
    public GetBookByIdResponse getById(@PathVariable int id){

        return bookService.getBookByIdResponse(id);
    }

    @DeleteMapping("{id}")

    public void deleteById(@PathVariable int id){
      bookService.deleteBookById(id);
    }

    @PutMapping()

    public UpdateBookResponse updateBook(@Valid @RequestBody UpdateBookRequest request){
        return bookService.updateBook(request);
    }

    @GetMapping("search")
    public List<SearchBookResponse> search(SearchBookRequest request){
        //
        return bookService.search(request);
    }




}
