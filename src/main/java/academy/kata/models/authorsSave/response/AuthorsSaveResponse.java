package academy.kata.models.authorsSave.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorsSaveResponse {

    private Long authorId;
}
