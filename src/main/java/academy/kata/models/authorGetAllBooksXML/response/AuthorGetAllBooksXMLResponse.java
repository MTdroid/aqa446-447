package academy.kata.models.authorGetAllBooksXML.response;

import academy.kata.entity.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "authors_books")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorGetAllBooksXMLResponse {

    @XmlElement(name = "book_title", required = true)
    @JsonProperty("bookTitle")
    private String bookTitle;

    @XmlElement(name = "author", required = true)
    @JsonProperty("author")
    private Author author;
}
