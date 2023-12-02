package academy.kata.steps.checkResponse;

import academy.kata.models.authorsSave.response.AuthorsSaveResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorSave {

    public static void checkResponse(AuthorsSaveResponse author) {
        assertNotNull(author.getAuthorId());
    }

    public static void AuthorSaveResponseBody(AuthorsSaveResponse author, Long id) {
        assertEquals(id, author.getAuthorId());
    }
}
