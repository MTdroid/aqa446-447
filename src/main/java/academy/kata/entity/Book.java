package academy.kata.entity;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Timestamp;

@Data
public class Book {

    private Long id;
    private String bookTitle;
    private Long authorId;

    @XmlElement(name = "timestamp", nillable = true)
    private Timestamp updated;
}
