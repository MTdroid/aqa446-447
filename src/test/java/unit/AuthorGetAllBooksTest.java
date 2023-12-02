
package unit;

import academy.kata.models.ErrorResponse;
import academy.kata.models.authorGetAllBooks.response.AuthorGetAllBooksResponse;
import academy.kata.models.authorsSave.response.AuthorsSaveResponse;
import academy.kata.models.booksSave.response.BooksSaveResponse;
import academy.kata.rest.ErrorRequestSpecification;
import academy.kata.rest.PositiveRequestSpecification;
import academy.kata.steps.checkResponse.AuthorGetAllBooks;
import academy.kata.steps.checkResponse.AuthorSave;
import academy.kata.steps.checkResponse.BooksSave;
import academy.kata.steps.checkResponse.ErrorResponseCheck;
import academy.kata.utils.TestDataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@Epic("API тесты")
@Story("Получение всех книг автора")
public class AuthorGetAllBooksTest {
    String firstName = TestDataGenerator.generateData();
    String familyName=TestDataGenerator.generateData();
    String secondName=TestDataGenerator.generateData();
    String bookTitle= TestDataGenerator.generateData();
    String date = TestDataGenerator.generateDate();
    ErrorResponseCheck errorResponseCheck = new ErrorResponseCheck();

    @Test
    @DisplayName("Получение всех книг автора с проверкой тела ответа")
    @Description("Позитивный тест. В консоль должны вывестись все книги автора")
    public void getAllBooksTest() {

        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName,familyName,secondName, date,201);
        AuthorSave.checkResponse(author);

        BooksSaveResponse booksSaveResponse = PositiveRequestSpecification.booksSaveResponse(bookTitle, author.getAuthorId(), 201);
        BooksSave.checkResponse(booksSaveResponse);

        List<AuthorGetAllBooksResponse> authorGetAllBooksResponses = PositiveRequestSpecification.authorGetAllBooksResponse(author.getAuthorId().intValue(), 200);
        AuthorGetAllBooks.checkResponseBody(authorGetAllBooksResponses,bookTitle,firstName,familyName,secondName);
    }

    @Test
    @DisplayName("Получение всех книг несуществующего автора")
    @Description("Негативный тест. В консоль должны вывестись errorCode,errorMessage")
    public void getAllBooksInvalidId() {

        ErrorResponse errorResponse =ErrorRequestSpecification.authorGetAllBooksResponseErr(0,409);
        errorResponseCheck.checkResponse(errorResponse,"1004",null,"Указанный автор не существует в таблице");
    }
}

