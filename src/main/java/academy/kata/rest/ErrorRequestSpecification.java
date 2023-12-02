package academy.kata.rest;

import academy.kata.models.ErrorResponse;
import academy.kata.models.authorGetAllBooksXML.request.AuthorGetAllBooksXMLRequest;
import academy.kata.models.authorsSave.request.AuthorForBookSave;
import academy.kata.models.booksSave.request.BooksSaveRequest;

import static academy.kata.rest.PositiveRequestSpecification.*;
import static io.restassured.RestAssured.given;

public class ErrorRequestSpecification {

    public static ErrorResponse booksSaveResponseErr(String bookTitle, Long authorId, Integer code) {
        AuthorForBookSave author = new AuthorForBookSave(authorId);
        BooksSaveRequest book = new BooksSaveRequest(bookTitle, author);

        return given()
                .spec(requestSpecification())
                .body(book)
                .when()
                .post(Endpoint.booksSave)
                .then()
                .assertThat()
                .spec(statusCode(code))
                .extract().as(ErrorResponse.class);
    }

    public static ErrorResponse authorGetAllBooksResponseErr(Integer authorId, Integer code) {

        return given()
                .spec(requestSpecification())
                .when()
                .get(Endpoint.authorGetAllBooks, authorId)
                .then()
                .assertThat()
                .spec(statusCode(code))
                .extract().as(ErrorResponse.class);
    }

    public static ErrorResponse authorGetAllBooksXMLResponseErr(Integer authorId, Integer code) {
        AuthorGetAllBooksXMLRequest author = new AuthorGetAllBooksXMLRequest();
        author.setId(authorId);

        return given()
                .spec(requestSpecificationXML())
                .body(author)
                .when()
                .post(Endpoint.authorGetAllBooksXML)
                .then()
                .assertThat()
                .spec(statusCode(code))
                .extract().as(ErrorResponse.class);
    }
}
