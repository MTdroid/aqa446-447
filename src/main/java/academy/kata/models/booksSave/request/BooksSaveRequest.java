package academy.kata.models.booksSave.request;

import academy.kata.models.authorsSave.request.AuthorForBookSave;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class BooksSaveRequest {

    private String bookTitle;
    private AuthorForBookSave author;
}
