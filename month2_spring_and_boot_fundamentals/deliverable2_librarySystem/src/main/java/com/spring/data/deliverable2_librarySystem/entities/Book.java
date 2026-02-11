package com.spring.data.deliverable2_librarySystem.entities;

import com.spring.data.deliverable2_librarySystem.entities.enums.Genre;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true)
    private String isbn;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Enumerated(
            EnumType.STRING
    )
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "author_id",
            nullable = false
    )
    private Author author;

    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Loan> loans;

    public Book() {
    }

    public Book(String title, String isbn, Integer publicationYear, Genre genre) {
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }
}
