package unit;

import academy.kata.models.ErrorResponse;
import academy.kata.models.authorsSave.response.AuthorsSaveResponse;
import academy.kata.models.booksSave.response.BooksSaveResponse;
import academy.kata.rest.ErrorRequestSpecification;
import academy.kata.rest.PositiveRequestSpecification;
import academy.kata.steps.checkResponse.BooksSave;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("API тесты")
@Story("Сохранение книги")
public class BooksSaveTest {
    String firstName =TestDataGenerator.generateData();
    String familyName=TestDataGenerator.generateData();
    String secondName=TestDataGenerator.generateData();
    String bookTitle= TestDataGenerator.generateData();

    @Test
    @DisplayName("Успешное создание новой книги")
    @Description("Позитивный тест. Проверка, что книга успешно создалась")
    public void bookSave() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, secondName, 201);

        BooksSaveResponse booksSaveResponse = PositiveRequestSpecification.booksSaveResponse(bookTitle, author.getAuthorId(), 201);
        BooksSave.checkResponse(booksSaveResponse);
    }

    @Test
    @DisplayName("Cоздание новой книги с пустым firsName")
    @Description("Негативный тест. Проверка, что в консоли появилась ошибка")
    public void bookSaveWithoutTitle() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, secondName, 201);

        ErrorResponse errorResponse = ErrorRequestSpecification.booksSaveResponseErr("",author.getAuthorId(), 400);
        ErrorRequestSpecification.errorRespons(errorResponse,"1001","Валидация не пройдена", "Некорректный размер поля firstName");
    }

    @Test
    @DisplayName("Cоздание новой книги с несуществующим автором")
    @Description("Негативный тест. Проверка, что в консоли появилась ошибка")
    public void bookSaveWithInvalidAuthorId() {
        ErrorResponse errorResponse = ErrorRequestSpecification.booksSaveResponseErr(bookTitle, 866L, 409);
        ErrorRequestSpecification.errorRespons(errorResponse,"","1004", "Указанный автор не существует в таблице");
    }

    @Test
    @DisplayName("Cоздание новой книги bookTitle >100 символов")
    @Description("Негативный тест. Проверка, что в консоли появилась ошибка")
    public void bookSaveWithInvalidBookTitle() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, secondName, 201);

        ErrorResponse errorResponse = ErrorRequestSpecification.booksSaveResponseErr(TestDataGenerator.generateData101symb(), author.getAuthorId(), 400);
        ErrorRequestSpecification.errorRespons(errorResponse,"1001","Валидация не пройдена", "Некорректный размер поля firstName");
    }

    @Test
    @DisplayName("Cоздание новой книги c проверкой тела ответа")
    @Description("Позитивный тест. Проверка, тела ответа(id)")
    public void bookSaveCheckBody() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, secondName, 201);

        BooksSaveResponse booksSaveResponse = PositiveRequestSpecification.booksSaveResponse(bookTitle, author.getAuthorId(), 201);
        BooksSave.booksSaveResponseBody(booksSaveResponse, booksSaveResponse.getBookId());
    }
}