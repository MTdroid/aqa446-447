package academy.kata.models.authorGetAllBooks.response;


import academy.kata.entity.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;


@Data
@JsonIgnoreProperties(allowSetters = true)
public class AuthorGetAllBooksResponse {

    @XmlElement(name = "book_title", required = true)
    @JsonProperty("bookTitle")
    private String bookTitle;

    @XmlElement(name = "author", required = true)
    @JsonProperty("author")
    private Author author;

    @XmlElement(name = "updated")
    @JsonProperty("updated")
    private Date updated;
}

