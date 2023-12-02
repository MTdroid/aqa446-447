package academy.kata.models.booksSave.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BooksSaveResponse {

    private Long bookId;
}
