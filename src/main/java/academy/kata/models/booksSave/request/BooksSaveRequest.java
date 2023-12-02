package academy.kata.models.booksSave.request;

import academy.kata.models.authorsSave.request.AuthorForBookSave;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BooksSaveRequest {

    private String bookTitle;
    private AuthorForBookSave author;
}
