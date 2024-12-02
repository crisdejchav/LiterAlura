package dev.crisdejchav.LiterAlura.services;


import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter {

    public static <T> T convertJsonToObject(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
