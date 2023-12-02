package academy.kata.steps.checkResponse;

import academy.kata.models.booksSave.response.BooksSaveResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BooksSave {

    public static void checkResponse(BooksSaveResponse booksSaveResponse) {
        assertNotNull(booksSaveResponse.getBookId());
    }

    public static void booksSaveResponseBody(BooksSaveResponse booksSaveResponse, Long id) {
        assertEquals(id, booksSaveResponse.getBookId());
    }
}
