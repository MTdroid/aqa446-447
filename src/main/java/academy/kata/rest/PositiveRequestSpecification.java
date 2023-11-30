package academy.kata.rest;

import academy.kata.models.authorGetAllBooks.response.AuthorGetAllBooksResponse;
import academy.kata.models.authorGetAllBooksXML.request.AuthorGetAllBooksXMLRequest;
import academy.kata.models.authorGetAllBooksXML.response.AuthorGetAllBooksXMLResponse;
import academy.kata.models.authorsSave.request.AuthorForBookSave;
import academy.kata.models.authorsSave.request.AuthorsSaveRequest;
import academy.kata.models.authorsSave.response.AuthorsSaveResponse;
import academy.kata.models.booksSave.request.BooksSaveRequest;
import academy.kata.models.booksSave.response.BooksSaveResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class PositiveRequestSpecification {
    public static RequestSpecification requestSpecification() {

        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static RequestSpecification requestSpecificationXML() {

        return new RequestSpecBuilder()
                .setContentType(ContentType.XML)
                .setBaseUri("http://localhost:8080")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static ResponseSpecification statusCode(Integer code) {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
                .expectStatusCode(code);
        return builder.build();
    }

    public static AuthorsSaveResponse authorsSaveResponse(String firstName, String familyName, String secondName, Integer code) {
        AuthorsSaveRequest author = new AuthorsSaveRequest(firstName, familyName, secondName);
        return given()
                .spec(requestSpecification())
                .body(author)
                .when()
                .post(Endpoint.authorsSave)
                .then()
                .assertThat()
                .spec(statusCode(code))
                .extract().as(AuthorsSaveResponse.class);
    }

    public static List<AuthorGetAllBooksResponse> authorGetAllBooksResponse(int authorId, Integer code) {

        return given()
                .spec(requestSpecification())
                .when()
                .get(Endpoint.authorGetAllBooks, authorId)
                .then()
                .assertThat()
                .spec(statusCode(code))
                .extract().jsonPath().getList(".", AuthorGetAllBooksResponse.class);

    }

    public static List<AuthorGetAllBooksXMLResponse> authorGetAllBooksXMLResponse(int authorId, Integer code) {
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
                .extract().xmlPath().getList("", AuthorGetAllBooksXMLResponse.class);
    }

    public static BooksSaveResponse booksSaveResponse(String bookTitle, Long authorId, Integer code) {
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
                .extract().as(BooksSaveResponse.class);

    }
    public static List<AuthorGetAllBooksResponse> authorGetAllBooksResponseBody(int authorId, Integer code) {

        return given()
                .spec(requestSpecification())
                .when()
                .get(Endpoint.authorGetAllBooks, authorId)
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UsersTemplate.json"))
                .spec(statusCode(code))
                .extract().jsonPath().getList(".", AuthorGetAllBooksResponse.class);

    }
    public static BooksSaveResponse booksSaveResponseBODY(String bookTitle, Long authorId, Integer code) {
        AuthorForBookSave author = new AuthorForBookSave(authorId);
        BooksSaveRequest book = new BooksSaveRequest(bookTitle, author);
        return
                given()
                        .spec(requestSpecification())
                        .when()
                        .contentType("application/json")
                        .get("http://localhost:8080/bookEntities/"+authorId).then()
                        .body("bookTitle",equalTo(book.getBookTitle()))
                        .extract().as(BooksSaveResponse.class);
    }
}


