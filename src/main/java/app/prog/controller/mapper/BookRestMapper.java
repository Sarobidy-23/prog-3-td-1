package app.prog.controller.mapper;

import app.prog.controller.request.BookRequest;
import app.prog.controller.response.BookResponse;
import app.prog.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookRestMapper {
    public BookResponse toRest(Book domain) {
        return BookResponse.builder()
                .id(domain.getId())
                .title(domain.getTitle())
                .author(domain.getAuthor())
                .hasAuthor(domain.hasAuthor())
                .build();
    }

    public Book toCreate(BookRequest data) {
        return Book.builder()
                .title(data.getTitle())
                .pageNumber(100)
                .author(data.getAuthor())
                .build();
    }
}
