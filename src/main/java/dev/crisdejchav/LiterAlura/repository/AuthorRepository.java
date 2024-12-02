package dev.crisdejchav.LiterAlura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.crisdejchav.LiterAlura.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);

    Optional<Author> findByDateBirth(Long dateBirth);

    Optional<Author> findByDateDeath(Long dateDeath);

    @Query(value = "SELECT * FROM authors WHERE id = :id", nativeQuery = true)
    Optional<Author> locateId(Long id);

    @Query("SELECT a FROM Author a WHERE a.dateBirth <= :year AND (a.dateDeath IS NULL OR a.dateDeath >= :year)")
    List<Author> findByYearRange(@Param("year") Long year);

    
} 