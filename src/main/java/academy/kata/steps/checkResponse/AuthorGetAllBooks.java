package academy.kata.steps.checkResponse;

import academy.kata.models.authorGetAllBooks.response.AuthorGetAllBooksResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorGetAllBooks {

    public static void checkResponseBody(List<AuthorGetAllBooksResponse> authorGetAllBooksResponses, String bookTitile,String firstName,String familyName,String secondName) {
        assertThat(authorGetAllBooksResponses).isNotNull();
        assertEquals(authorGetAllBooksResponses.get(0).getBookTitle(), bookTitile);
        assertEquals(authorGetAllBooksResponses.get(0).getAuthor().getFirstName(), firstName);
        assertEquals(authorGetAllBooksResponses.get(0).getAuthor().getFamilyName(), familyName);
        assertEquals(authorGetAllBooksResponses.get(0).getAuthor().getSecondName(), secondName);
    }
}
