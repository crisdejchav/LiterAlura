package dev.crisdejchav.LiterAlura.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorData(
    @JsonProperty("name")
    String name,
    @JsonProperty("birth_year")
    Long birth,
    @JsonProperty("death_year")
    Long death
) {

    public AuthorData getFromAuthor(Author author){
        return new AuthorData(author.getName(), author.getDateBirth(), author.getDateDeath());
    }
    
    public String toString(){
        return "Name: " + name + "\n" + "Birth Year: " + birth + "\n" + "Death Year: " + death;
    }
}
