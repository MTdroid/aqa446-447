
package unit;

import academy.kata.entity.Author;
import academy.kata.models.ErrorResponse;
import academy.kata.models.authorGetAllBooksXML.response.AuthorGetAllBooksXMLResponse;
import academy.kata.models.authorsSave.response.AuthorsSaveResponse;
import academy.kata.models.booksSave.response.BooksSaveResponse;
import academy.kata.rest.ErrorRequestSpecification;
import academy.kata.rest.PositiveRequestSpecification;
import academy.kata.steps.checkResponse.*;
import academy.kata.utils.TestDataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@Epic("API тесты")
@Story("Получение всех книг автора XML")
public class AuthorGetAllBooksXMLTest {
    String firstName = TestDataGenerator.generateData();
    String familyName=TestDataGenerator.generateData();
    String secondName=TestDataGenerator.generateData();
    String bookTitle= TestDataGenerator.generateData();
    String date = TestDataGenerator.generateDate();
    ErrorResponseCheck errorResponseCheck = new ErrorResponseCheck();

    @Test
    @DisplayName("Получение всех книг автора в формате XML с проверкой тела ответа")
    @Description("Позитивный тест. В консоль должны вывестись все книги автора")
    public void getAllBooksXMLTest() {

        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName,familyName,secondName, date,201);
        AuthorSave.checkResponse(author);

        BooksSaveResponse booksSaveResponse = PositiveRequestSpecification.booksSaveResponse(bookTitle, author.getAuthorId(), 201);
        BooksSave.checkResponse(booksSaveResponse);

        List<AuthorGetAllBooksXMLResponse> authorGetAllBooksXMLResponses = PositiveRequestSpecification.authorGetAllBooksXMLResponse(author.getAuthorId().intValue(), 200);
        Author author1 =authorGetAllBooksXMLResponses.get(0).getAuthor();
        AuthorGetAllBooksXML.checkResponseBody(authorGetAllBooksXMLResponses,author1);

    }

        @Test
        @DisplayName("Получение всех книг несуществующего автора")
        @Description("Негативный тест. В консоль должны вывестись errorCode,errorMessage")
        public void getAllBooksXmlInvalidID() {
            ErrorResponse errorResponse = ErrorRequestSpecification.authorGetAllBooksXMLResponseErr(909,409);
            errorResponseCheck.checkResponse(errorResponse,"1004",null,"Указанный автор не существует в таблице");
        }
}

