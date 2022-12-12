package app.prog.controller.mapper;

import app.prog.controller.response.BookResponse;
import app.prog.controller.response.CreateBookResponse;
import app.prog.controller.response.UpdateBookResponse;
import app.prog.model.AuthorEntity;
import app.prog.model.BookEntity;
import app.prog.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookRestMapper {
    private final AuthorService authorService;
    public BookResponse toRest(BookEntity domain) {
        return BookResponse.builder()
                .id(domain.getId())
                .category(domain.getCategories().stream().map(categoryEntity -> {return categoryEntity.getName();}).toList())
                .title(domain.getTitle())
                .author(domain.getAuthor() != null ? domain.getAuthor().getName() : null)
                .hasAuthor(domain.hasAuthor())
                .build();
    }

    public BookEntity toDomain(CreateBookResponse rest) {
        AuthorEntity associatedAuthor = null;
        if(rest.getAuthor() != null){
            associatedAuthor = authorService.findAuthorByName(rest.getAuthor());
        }

        return BookEntity.builder()
                .author_id(associatedAuthor != null ? associatedAuthor.getId() : null)
                .author(associatedAuthor)
                .title(rest.getTitle())
                .pageNumber(0) //Constraint not null in database, default value is 0
                .build();
    }

    public BookEntity toDomain(UpdateBookResponse rest) {
        AuthorEntity associatedAuthor = null;
        if(rest.getAuthor() != null){
            associatedAuthor = authorService.findAuthorByName(rest.getAuthor());
        }

        return BookEntity.builder()
                .id(rest.getId())
                .author(associatedAuthor)
                .title(rest.getTitle())
                .pageNumber(0) //Constraint not null in database, default value is 0
                .build();
    }
}
