package dev.crisdejchav.LiterAlura.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "authors")
public class Author {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(unique = true)
    private String name;



    @Getter
    @Column(name = "birth_year")
    private Long dateBirth;

    @Getter
    @Column(name = "death_year")
    private Long dateDeath;

    public Author() {
    }

    public Author(AuthorData authorData) {
        this.name = authorData.name();
        this.dateBirth = authorData.birth();
        this.dateDeath = authorData.death();
    }

}
