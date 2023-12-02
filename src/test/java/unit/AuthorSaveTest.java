package unit;

import academy.kata.models.authorsSave.response.AuthorsSaveResponse;
import academy.kata.rest.PositiveRequestSpecification;
import academy.kata.steps.checkResponse.AuthorSave;
import academy.kata.utils.TestDataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("API тесты")
@Story("Сохранение автора")
public class AuthorSaveTest {
    String firstName = TestDataGenerator.generateData();
    String familyName=TestDataGenerator.generateData();
    String secondName=TestDataGenerator.generateData();
    String date = TestDataGenerator.generateDate();

    @Test
    @DisplayName("Добавление нового автора")
    @Description("Позитивный тест. Должен добавиться автор с уникальным id")
    public void saveAuthor() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName,familyName,secondName, date,201);
        AuthorSave.checkResponse(author);

    }

    @Test
    @DisplayName("Добавление нового автора без secondName")
    @Description("Позитивный тест. Должен добавиться автор с уникальным id и пустым secondName")
    public void saveAuthorWithoutSecondName() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, "", date,201);
        AuthorSave.checkResponse(author);
    }

    @Test
    @DisplayName("Добавление нового автора с проверкой тела ответа")
    @Description("Позитивный тест. Должен добавиться автор с уникальным id")
    public void saveAuthorCheckBody() {
        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName, familyName, secondName,date, 201);
        AuthorSave.AuthorSaveResponseBody(author,author.getAuthorId());

    }
}
