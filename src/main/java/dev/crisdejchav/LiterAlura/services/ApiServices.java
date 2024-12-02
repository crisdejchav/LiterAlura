package dev.crisdejchav.LiterAlura.services;

import dev.crisdejchav.LiterAlura.model.BookData;
import dev.crisdejchav.LiterAlura.model.SearchData;

public class ApiServices {
    public static BookData[] findBook(String filter, String title){
        ApiConsumer apiConsumer = new ApiConsumer();
        String json = apiConsumer.consumeData("https://gutendex.com/books/?"+filter+"=" + title.replaceAll(" ", "%20"));
        BookData[] books = DataConverter.convertJsonToObject(json, SearchData.class).books();
        return books;
    }

}
