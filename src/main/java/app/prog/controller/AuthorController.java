package app.prog.controller;

import app.prog.controller.mapper.AuthorRestMapper;
import app.prog.controller.response.AuthorResponse;
import app.prog.controller.response.CreateAuthorResponse;
import app.prog.controller.response.UpdateAuthorResponse;
import app.prog.model.AuthorEntity;
import app.prog.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AuthorController {
    private final AuthorService service;
    private final AuthorRestMapper mapper;

    @GetMapping("/authors")
    public List<AuthorResponse> getAuthors(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        return service.getAuthor(page, pageSize).stream().map(mapper::toRest).toList();
    }


    @PostMapping("/authors")
    public List<AuthorResponse> createAuthors(@RequestBody List<CreateAuthorResponse> toCreate) {
        List<AuthorEntity> domain = toCreate.stream().map(mapper::toDomain).toList();

        return service.createAuthor(domain).stream().map(mapper::toRest).toList();
    }
    @PutMapping("/authors")
    public List<AuthorResponse> updateAuthors(@RequestBody List<UpdateAuthorResponse> toUpdate) {
        List<AuthorEntity> domain = toUpdate.stream().map(mapper::toDomain).toList();

        return service.updateAuthor(domain).stream().map(mapper::toRest).toList();
    }
    @DeleteMapping("/authors/{idAuthor}")
    public AuthorResponse deleteAuthors(@PathVariable int idAuthor) {
        return mapper.toRest(service.deleteAuthor(idAuthor));
    }
}
