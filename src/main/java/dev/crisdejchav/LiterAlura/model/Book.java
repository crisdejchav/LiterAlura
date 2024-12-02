package dev.crisdejchav.LiterAlura.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "books")
public class Book {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(unique = true)
    private int webid;

    @Getter
    @Column(unique = true)
    private String title;

    @Getter
    @Column
    private Long author;

    @Getter
    @Column
    private String category;

    @Getter
    @Column
    private String languages;

    @Getter
    @Column
    private int downloads;

    public Book() {
    }

    public Book(BookData bookData, Long author) {
        this.webid = bookData.id();
        this.title = bookData.title();
        this.category = bookData.category()[0];
        this.languages = bookData.languages()[0];
        this.downloads = bookData.downloadCount();
        this.author = author;
    }



}
