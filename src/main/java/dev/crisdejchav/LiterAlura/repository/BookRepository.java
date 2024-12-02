package dev.crisdejchav.LiterAlura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.crisdejchav.LiterAlura.model.Author;
import dev.crisdejchav.LiterAlura.model.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByTitle(String title);

    List<Book> findByAuthor(Author author);

    @Query(value="SELECT * FROM books", nativeQuery = true)
    List<Book> listAll();

    @Query("SELECT DISTINCT b.languages from Book b")
    List<String> listAllLanguages();

}
