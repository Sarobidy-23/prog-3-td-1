package app.prog.controller.mapper;

import app.prog.controller.response.AuthorResponse;
import app.prog.controller.response.CreateAuthorResponse;
import app.prog.controller.response.UpdateAuthorResponse;
import app.prog.model.AuthorEntity;
import app.prog.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthorRestMapper {
    private final AuthorService authorService;
    public AuthorResponse toRest(AuthorEntity domain) {
        return AuthorResponse.builder()
                .id(domain.getId())
                .name(domain.getName())
                .particularity(domain.getParticularity())
                .hasParticularity(domain.hasParticularity())
                .build();
    }

    public AuthorEntity toDomain(CreateAuthorResponse rest) {
        return AuthorEntity.builder()
                .name(rest.getName())
                .particularity(rest.getParticularity())
                .build();
    }
    public AuthorEntity toDomain(UpdateAuthorResponse rest) {
        AuthorEntity toUpdate = authorService.findAUthorById(rest.getId());
             toUpdate.setName(rest.getName());
             toUpdate.setParticularity(rest.getParticularity());
        return toUpdate;
    }
}
