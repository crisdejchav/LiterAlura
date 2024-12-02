package dev.crisdejchav.LiterAlura.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
    @JsonProperty("id")
    int id,
    @JsonProperty("title")
    String title,
    @JsonProperty("authors")
    AuthorData[] authors,
    @JsonProperty("subjects")
    String[] category,
    @JsonProperty("languages")
    String[] languages,
    @JsonProperty("download_count")
    int downloadCount
) {
    @Override
    public final String toString() {
        String author = authors[0].name();  
        return "Title: " + title + "\n" + "Author: " + author+ "\n" + "Category: " + category[0] + "\n" + "Language: " + languages[0] + "\n Download Count: " + downloadCount;
    }

    public static BookData getFromBook(Book book, Author author){
        return new BookData(book.getWebid(), book.getTitle(), new AuthorData[]{new AuthorData(author.getName(),author.getDateBirth(),author.getDateDeath())}, new String[]{book.getCategory()}, new String[]{book.getLanguages()}, book.getDownloads());
    }
}