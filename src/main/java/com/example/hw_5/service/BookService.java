package com.example.hw_5.service;

import com.example.hw_5.core.exception.BookBusinessException;
import com.example.hw_5.dto.book.request.CreateBookRequest;
import com.example.hw_5.dto.book.request.SearchBookRequest;
import com.example.hw_5.dto.book.request.UpdateBookRequest;
import com.example.hw_5.dto.book.response.*;
import com.example.hw_5.entity.*;
import com.example.hw_5.mapper.BookMapper;
import com.example.hw_5.repository.BookCopyRepository;
import com.example.hw_5.repository.BookRepository;
import com.example.hw_5.rules.BookBusinessRules;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class BookService {

    private final BookRepository bookRepository;

    private final CategoryService categoryService;

    private final AuthorService authorService;

    private final PublisherService publisherService;

    private final BookBusinessRules bookBusinessRules;

    private final BookCopyRepository bookCopyRepository;


    public BookService(BookRepository bookRepository, CategoryService categoryService, AuthorService authorService, PublisherService publisherService, BookBusinessRules bookBusinessRules, BookCopyRepository bookCopyRepository) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.bookBusinessRules=bookBusinessRules;
        this.bookCopyRepository = bookCopyRepository;
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

    public UpdateBookResponse updateTotalCopies(UpdateBookRequest request){
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
                .findCategoryById(createBookRequest.getCategoryId()).orElseThrow(GetBookParamsResponse("Bu id ile bir kategori bulunamadı"));

        book.setCategory(category);
   */
        book = bookRepository.save(book);

        List<BookCopy> copies = new ArrayList<>();
        for (int i = 0; i < createBookRequest.getTotalCopies(); i++) {
            BookCopy copy = new BookCopy();
            copy.setBook(book);
            copy.setAvailable(true); // ilk eklenince müsaittir
            copies.add(copy);
        }

// Hepsini kaydet
        bookCopyRepository.saveAll(copies);

// book içine kopyaları set et
        book.setCopies(copies);
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

    public List<GetBookParamsResponse> getBooksByParams(String isbnNumber, String name, int authorId, String status){
        List<Book> bookList = new ArrayList<>();

        bookList = bookRepository.searchCustomSql(isbnNumber,name,authorId,status);

        List<GetBookParamsResponse> responseList = new ArrayList<>();
        BookMapper bookMapper = BookMapper.INSTANCE;

        for (Book book: bookList) {
           responseList.add(bookMapper.bookToGetBookParamsResponse(book));
        }

        return responseList;
    }

    public GetBookParamsResponse updateTotalCopies(int bookId, int delta)
    {
        Book book = bookRepository.findById(bookId).stream().findFirst().orElseThrow(()-> new BookBusinessException("Kitap bulunamadı !"));

        if(book.getTotalCopies() + delta < 0)
        {
            throw new BookBusinessException("Total copies negatif olamaz !");
        }

        book.setTotalCopies( book.getTotalCopies() + delta);
        bookRepository.save(book);

        BookMapper bookMapper = BookMapper.INSTANCE;
        return bookMapper.bookToGetBookParamsResponse(book);
    }
}
