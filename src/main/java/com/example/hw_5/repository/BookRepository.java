package com.example.hw_5.repository;

import com.example.hw_5.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    //Derived Query Methods
    // Select * from products where name like '{name}'
    List<Book> findByNameLike(String name);
    // Derived Query

    // Native query false: JPQL
    // Native query true: SAF SQL

    @Query(value="Select b from Book b Where UPPER(b.isbnNumber) LIKE UPPER(:isbnNumber)", nativeQuery = false)// NativeQuery -> SAF SQL - JPA + SQL = JPQL
    List<Book> search(String isbnNumber); // Derived query değil

    @Query(value="Select * from book where LOWER(isbn_number) LIKE LOWER(:isbn_number)", nativeQuery = true)// NativeQuery -> SAF SQL - JPA + SQL = JPQL
    List<Book> searchSql(@Param("isbn_number") String isbnNumber); // Derived query değil

    // isbne göre arama
    boolean existsByIsbnNumber(String isbnNumber);


    @Query(value = """
    SELECT * 
    FROM book b 
    WHERE b.isbn_number = :isbnNumber
      AND b.name = :name
      AND b.author_id = :authorId
      AND b.status = :status
""", nativeQuery = true)
    List<Book> searchCustomSql(
            @Param("isbnNumber") String isbnNumber,
            @Param("name") String name,
            @Param("authorId") int authorId,
            @Param("status") String status
    );

}
