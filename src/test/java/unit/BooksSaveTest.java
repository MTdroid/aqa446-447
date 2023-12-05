
package unit;

import academy.kata.models.ErrorResponse;
import academy.kata.models.authorsSave.response.AuthorsSaveResponse;
import academy.kata.models.booksSave.response.BooksSaveResponse;
import academy.kata.rest.ErrorRequestSpecification;
import academy.kata.rest.PositiveRequestSpecification;
import academy.kata.steps.checkResponse.BooksSave;
import academy.kata.steps.checkResponse.ErrorResponseCheck;
import academy.kata.steps.checkResponse.HibernateDbCheck;
import academy.kata.utils.TestDataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("API тесты")
@Story("Сохранение книги")
public class BooksSaveTest {
    String firstName = TestDataGenerator.generateData();
    String familyName = TestDataGenerator.generateData();
    String secondName = TestDataGenerator.generateData();
    String bookTitle = TestDataGenerator.generateData();
    String date = TestDataGenerator.generateDate();
    ErrorResponseCheck errorResponseCheck = new ErrorResponseCheck();

    @Test
    @DisplayName("Успешное создание новой книги")
    @Description("Позитивный тест. Проверка, что книга успешно создалась")
    public void bookSave() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, secondName, date, 201);
        BooksSaveResponse booksSaveResponse = PositiveRequestSpecification.booksSaveResponse(bookTitle, author.getAuthorId(), 201);
        BooksSave.checkResponse(booksSaveResponse);
        HibernateDbCheck.compareBookPositive(bookTitle, author.getAuthorId(),booksSaveResponse.getBookId());
        HibernateDbCheck.compareBookResponsePositive(booksSaveResponse,bookTitle);

    }

    @Test
    @DisplayName("Cоздание новой книги с пустым bookTitle")
    @Description("Негативный тест. Проверка, что в консоли появилась ошибка")
    public void bookSaveWithoutTitle() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, secondName, date, 201);
        ErrorResponse errorResponse = ErrorRequestSpecification.booksSaveResponseErr("", author.getAuthorId(), 400);
        errorResponseCheck.checkResponse(errorResponse, "1001", "Валидация не пройдена", "Некорректный размер поля firstName");

    }

    @Test
    @DisplayName("Cоздание новой книги с null в bookTitle")
    @Description("Негативный тест. Проверка, что в консоли появилась ошибка")
    public void bookSaveTitleNull() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, secondName, date, 201);
        ErrorResponse errorResponse = ErrorRequestSpecification.booksSaveResponseErr(null, author.getAuthorId(), 400);
        errorResponseCheck.checkResponse(errorResponse, "1001", "Валидация не пройдена", "Не передан обязательный параметр: bookTitle");
        HibernateDbCheck.findBookNegative(null);
    }

    @Test
    @DisplayName("Cоздание новой книги с несуществующим автором")
    @Description("Негативный тест. Проверка, что в консоли появилась ошибка")
    public void bookSaveWithInvalidAuthorId() {
        ErrorResponse errorResponse = ErrorRequestSpecification.booksSaveResponseErr(bookTitle, 866L, 409);
        errorResponseCheck.checkResponse(errorResponse, "1004", null, "Указанный автор не существует в таблице");
        HibernateDbCheck.findBookNegative(bookTitle);
    }

    @Test
    @DisplayName("Cоздание новой книги bookTitle >100 символов")
    @Description("Негативный тест. Проверка, что в консоли появилась ошибка")
    public void bookSaveWithInvalidBookTitle() {
        String bookTitle = TestDataGenerator.generateData101symb();
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, secondName, date, 201);
        ErrorResponse errorResponse = ErrorRequestSpecification.booksSaveResponseErr(bookTitle, author.getAuthorId(), 400);
        errorResponseCheck.checkResponse(errorResponse, "1001", "Валидация не пройдена", "Некорректный размер поля firstName");
        HibernateDbCheck.findBookNegative(bookTitle);
    }

    @Test
    @DisplayName("Cоздание новой книги c проверкой тела ответа")
    @Description("Позитивный тест. Проверка, тела ответа(id)")
    public void bookSaveCheckBody() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, secondName, date, 201);
        BooksSaveResponse booksSaveResponse = PositiveRequestSpecification.booksSaveResponse(bookTitle, author.getAuthorId(), 201);
        BooksSave.booksSaveResponseBody(booksSaveResponse, booksSaveResponse.getBookId());
        HibernateDbCheck.compareBookPositive(bookTitle, author.getAuthorId(), booksSaveResponse.getBookId());
        HibernateDbCheck.compareBookResponsePositive(booksSaveResponse,bookTitle);
    }
}
