package academy.kata.steps.checkResponse;

import academy.kata.entity.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernateCheckListSize {

    public static void CheckListSize(List<Book> bookList, int expected){
        assertEquals(expected,bookList.size());
    }
}
