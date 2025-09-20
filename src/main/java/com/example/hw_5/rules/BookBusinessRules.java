package com.example.hw_5.rules;

import com.example.hw_5.core.exception.BookBusinessException;
import com.example.hw_5.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class BookBusinessRules {

    private  final BookRepository bookRepository;

    public BookBusinessRules(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 1. isbn benzersiz; totalCopies >= 0.
    public void isbnMustBeUniqueAndTotalCopiesMustBePositive(String isbnNumber, int totalCopies){

        if(bookRepository.existsByIsbnNumber(isbnNumber)){
            throw new BookBusinessException("Bu ISBN numarasına ait kitap var. ISBN numarasını değiştir");
        }
        if (totalCopies < 0) {
            throw new BookBusinessException("Total copies negatif olamaz!");
        }
    }

    // 2. availableCopies hiçbir koşulda totalCopies’i aşamaz ve negatif olamaz.
    public void availableCopiesCannotBiggerThanTotalCopies(int availableCopies , int totalCopies){
        if (availableCopies < 0) {
            throw new BookBusinessException("Available copies negatif olamaz!");
        }
        if (availableCopies > totalCopies) {
            throw new BookBusinessException("Available copies total copies değerini aşamaz!");
        }
    }

    //3. status=INACTIVE kitap ödünç verilemez ve rezervasyon alınamaz.
    public void inactiveBookCannotBarrowOrMakeReservation(String status){
        if ("INACTIVE".equalsIgnoreCase(status)) {
            throw new BookBusinessException("Bu kitap INACTIVE durumunda, ödünç alınamaz veya rezerve edilemez.");
        }
    }

}
