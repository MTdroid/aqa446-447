package academy.kata.entity;
import lombok.Data;

@Data
public class Book {
    private Long id;
    private String bookTitle;
    private Long authorId;
}
