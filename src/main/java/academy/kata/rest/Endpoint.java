package academy.kata.rest;

import lombok.Data;

@Data
public class Endpoint {

    public static final String authorsSave = "/library/authors/save";
    public static final String booksSave = "/library/books/save";
    public static final String authorGetAllBooks = "/library/authors/{id}/books";
    public static final String authorGetAllBooksXML = "/library/authors/books";
    public static final String getJwtToken = "http://localhost:8080/auth/login";
}