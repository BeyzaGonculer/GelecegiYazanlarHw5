package com.example.hw_5.service;

import com.example.hw_5.dto.book.request.CreateBookRequest;
import com.example.hw_5.dto.book.request.SearchBookRequest;
import com.example.hw_5.dto.book.request.UpdateBookRequest;
import com.example.hw_5.dto.book.response.*;
import com.example.hw_5.entity.*;
import com.example.hw_5.mapper.BookMapper;
import com.example.hw_5.repository.BookRepository;
import com.example.hw_5.repository.CategoryRepository;
import com.example.hw_5.rules.BookBusinessRules;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class BookService {

    private final BookRepository bookRepository;

    private final CategoryService categoryService;

    private final AuthorService authorService;

    private final PublisherService publisherService;

    private final BookBusinessRules bookBusinessRules;


    public BookService(BookRepository bookRepository, CategoryService categoryService, AuthorService authorService, PublisherService publisherService, BookBusinessRules bookBusinessRules) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.bookBusinessRules=bookBusinessRules;
    }

    public List<GetAllBookResponse> getAll(){

        List<Book> books = bookRepository.findAll();



        return books.stream()
                .map(book -> new GetAllBookResponse(
                        book.getBookId(),
                        book.getAuthor().getFirstName(),
                        book.getCategory().getCategoryName(),
                        book.getPageCount(),
                        book.getPublisher().getName(),
                        book.getIsbnNumber(),
                        book.getName()
                ))
                .toList();
    }

    public GetBookByIdResponse getBookByIdResponse(int id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        GetBookByIdResponse responseBook = new GetBookByIdResponse(book.getBookId(), book.getAuthor().getFirstName(), book.getCategory().getCategoryName(), book.getPageCount(), book.getPublisher().getName(), book.getIsbnNumber(), book.getName());
        return responseBook;
    }

    public UpdateBookResponse updateBook(UpdateBookRequest request){
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        book.setName(request.getName());
        book.setPageCount(request.getPageCount());
        book.setIsbnNumber(request.getIsbnNumber());

        // Eğer author, category, publisher id üzerinden değiştirilecekse
        // onların repository’sinden çekip set etmen gerekir:
        Author author = authorService.findAuthorById(request.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı"));
        book.setAuthor(author);

        Category category = categoryService.findCategoryById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
        book.setCategory(category);

        Publisher publisher = publisherService.findPublisherById(request.getPublisherId())
                .orElseThrow(() -> new RuntimeException("Yayınevi bulunamadı"));
        book.setPublisher(publisher);

        // Güncellenmiş kitabı kaydet
        Book updatedBook = bookRepository.save(book);

        // Response oluştur
        return new UpdateBookResponse(
                updatedBook.getBookId(),
                updatedBook.getAuthor().getFirstName(),
                updatedBook.getCategory().getCategoryName(),
                updatedBook.getPageCount(),
                updatedBook.getPublisher().getName(),
                updatedBook.getIsbnNumber(),
                updatedBook.getName()

        );
    }

    public void deleteBookById(int id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı"));

        bookRepository.delete(book);
    }


    public CreatedBookResponse add(@Valid CreateBookRequest createBookRequest)
    {
        //Book iş kuralları
        bookBusinessRules.inactiveBookCannotBarrowOrMakeReservation(createBookRequest.getStatus());
        bookBusinessRules.availableCopiesCannotBiggerThanTotalCopies(createBookRequest.getAvailableCopies(), createBookRequest.getTotalCopies());
        bookBusinessRules.isbnMustBeUniqueAndTotalCopiesMustBePositive(createBookRequest.getIsbnNumber(), createBookRequest.getTotalCopies());


        BookMapper bookMapper = BookMapper.INSTANCE;
        // Mapping
        Book book = bookMapper.createBookRequestToBook(createBookRequest);
      /*  Book book = new Book();
        book.setName(createBookRequest.getName());
        book.setIsbnNumber(createBookRequest.getIsbnNumber());
        book.setPageCount(createBookRequest.getPageCount());

        Author author = authorService
                .findAuthorById(createBookRequest.getAuthorId()).orElseThrow(()-> new NotFoundException("Bu id ile bir yazar bulunamadı"));

        book.setAuthor(author);

        Publisher publisher = publisherService
                .findPublisherById(createBookRequest.getPublisherId()).orElseThrow(()-> new NotFoundException("Bu id ile bir yazar bulunamadı"));

        book.setPublisher(publisher);


        Category category = categoryService
                .findCategoryById(createBookRequest.getCategoryId()).orElseThrow(()-> new NotFoundException("Bu id ile bir kategori bulunamadı"));

        book.setCategory(category);
   */
        book = bookRepository.save(book);

        return bookMapper.bookToCreateBookResponse(book);
    }

    public List<SearchBookResponse> search(@Valid SearchBookRequest request)
    {
        List<Book> bookList = bookRepository.searchSql("%"+request.getIsbnNumber()+"%");

        List<SearchBookResponse> responseList = new ArrayList<>();

        for (Book book:
                bookList) {
            SearchBookResponse response = new SearchBookResponse();
            response.setBookId(book.getBookId());
            response.setCategoryName(book.getCategory().getCategoryName());
            response.setAuthorName(book.getAuthor().getFirstName());
            response.setName(book.getName());
            response.setPageCount(book.getPageCount());
            response.setPublisherName(book.getPublisher().getName());
            response.setIsbnNumber(book.getIsbnNumber());
            responseList.add(response);


        }

        return responseList;
    }

}
