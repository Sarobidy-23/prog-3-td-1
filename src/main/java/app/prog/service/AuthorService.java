package app.prog.service;

import app.prog.model.AuthorEntity;
import app.prog.model.exception.NoSuchElementException;
import app.prog.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<AuthorEntity> getAuthor(Optional<Integer> page, Optional<Integer> pageSize) {
        Pageable pageable = PageRequest.of(page.orElse(0),pageSize.orElse(10) );
        return authorRepository.findAll(pageable).stream().toList();
    }
    public List<AuthorEntity> createAuthor(List<AuthorEntity> toCreate) {
        return authorRepository.saveAll(toCreate);
    }
    public List<AuthorEntity> updateAuthor(List<AuthorEntity> toUpdate) {
        return authorRepository.saveAll(toUpdate);
    }
    public AuthorEntity deleteAuthor(int idAuthor) {
        Optional<AuthorEntity> toDelete = authorRepository.findById(idAuthor);
        if(toDelete.isPresent()) {
            authorRepository.delete(toDelete.get());
            return toDelete.get();
        }else {
            throw new NoSuchElementException("Author.id:"+idAuthor+" not found");
        }
    }

    public AuthorEntity findAUthorById(int idAuthor) {
        Optional<AuthorEntity> toUpdate = authorRepository.findById(idAuthor);
        if(toUpdate.isPresent()) {
            return toUpdate.get();
        }else {
            throw new NoSuchElementException("Author.id:"+idAuthor+" not found");
        }
    }
    public AuthorEntity findAuthorByName(String nameAuthor) {
        Optional<AuthorEntity> toUpdate = authorRepository.findByName(nameAuthor);
        if(toUpdate.isPresent()) {
            return toUpdate.get();
        }else {
            throw new NoSuchElementException("Author.id:"+nameAuthor+" not found");
        }
    }
}
