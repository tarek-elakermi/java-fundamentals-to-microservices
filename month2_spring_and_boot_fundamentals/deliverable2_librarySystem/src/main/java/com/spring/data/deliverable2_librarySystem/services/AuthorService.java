package com.spring.data.deliverable2_librarySystem.services;

import com.spring.data.deliverable2_librarySystem.entities.Author;
import com.spring.data.deliverable2_librarySystem.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author createAuthor(Author author) {
//        Optional<Author> authorToPersist = authorRepository.findById(author.getId());
//        if(authorToPersist.isPresent()){
//            throw new RuntimeException("Author already exists can't added second time");
//        }
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
    }

    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = getAuthorById(id);
        author.setName(authorDetails.getName());
        author.setNationality(authorDetails.getNationality());
        author.setBirthDate(authorDetails.getBirthDate());
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId){
        authorRepository.deleteById(authorId);
    }








}
