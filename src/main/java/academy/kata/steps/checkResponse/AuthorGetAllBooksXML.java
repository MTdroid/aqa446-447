package academy.kata.steps.checkResponse;

import academy.kata.entity.Author;
import academy.kata.models.authorGetAllBooksXML.response.AuthorGetAllBooksXMLResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorGetAllBooksXML {

    public static void checkResponseBody(List<AuthorGetAllBooksXMLResponse> authorGetAllBooksXMLResponses, Author author) {
        assertThat(authorGetAllBooksXMLResponses).isNotNull();
        assertEquals(authorGetAllBooksXMLResponses.get(0).getAuthor(), author);
    }
}

