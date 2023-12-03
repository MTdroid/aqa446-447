package academy.kata.entity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "book")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "updated")
    @XmlElement(name = "updated", nillable = true)
    private Timestamp updated;
}
