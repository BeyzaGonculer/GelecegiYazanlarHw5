package com.example.hw_5.mapper;

import com.example.hw_5.dto.book.request.CreateBookRequest;
import com.example.hw_5.dto.book.response.CreatedBookResponse;
import com.example.hw_5.dto.book.response.GetBookParamsResponse;
import com.example.hw_5.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target="category.categoryId", source="categoryId")
    @Mapping(target="publisher.publisherId", source="publisherId")
    @Mapping(target="author.authorId", source="authorId")
    Book createBookRequestToBook(CreateBookRequest createBookRequest);

    @Mapping(target="categoryName", source="category.categoryName")
    @Mapping(target="publisherName", source="publisher.name")
    @Mapping(target="authorName", source="author.firstName")
    CreatedBookResponse bookToCreateBookResponse(Book book);

    @Mapping(target="categoryId", source="category.categoryId")
    @Mapping(target="publisherId", source="publisher.publisherId")
    @Mapping(target="authorId", source="author.authorId")
    GetBookParamsResponse bookToGetBookParamsResponse(Book book);

}
