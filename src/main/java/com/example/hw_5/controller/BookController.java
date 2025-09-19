package com.example.hw_5.controller;

import com.example.hw_5.dto.book.request.CreateBookRequest;
import com.example.hw_5.dto.book.request.SearchBookRequest;
import com.example.hw_5.dto.book.request.UpdateBookRequest;
import com.example.hw_5.dto.book.response.*;
import com.example.hw_5.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books") // localhost:port/api/v1/products ile başlıyorsa istek buraya gelsin.

public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Ekleme endpointleri ekleme sonrası durum için eklenen entity'i geri döner.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) // eğer işlem başarılı olursa, status code olarak şunu dön.
    public CreatedBookResponse add(@Valid @RequestBody CreateBookRequest request)
    {
        return bookService.add(request);
    }


    @GetMapping()
    public List<GetAllBookResponse> getAll() {
        return bookService.getAll();
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
        return bookService.updateTotalCopies(request);
    }

    @GetMapping("search")
    public List<SearchBookResponse> search(SearchBookRequest request){
        //
        return bookService.search(request);
    }

    @GetMapping("/params")
    public List<GetBookParamsResponse> getMembers(
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int authorId,
            @RequestParam(required = false) String status) {


        List<GetBookParamsResponse> books = bookService.getBooksByParams(isbn, name, authorId, status);
        return books;
    }

    @PatchMapping("/{id}/copies")
    public GetBookParamsResponse updateTotalCopies(
            @PathVariable int id,
            @RequestParam int delta) {
        return bookService.updateTotalCopies(id,delta);
    }


}
