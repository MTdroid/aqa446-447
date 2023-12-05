package academy.kata.steps.checkResponse;

import academy.kata.entity.Book;
import academy.kata.models.booksSave.response.BooksSaveResponse;
import academy.kata.repositoryClasses.BookRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HibernateDbCheck {
    static BookRepository bookRepository = new BookRepository();

    public static void checkListSize(List<Book> bookList, int expected){
        assertEquals(expected,bookList.size());
    }

    public static void  compareBookPositive(String bookTitle, Long authorId, Long bookId) {
        bookRepository.insertBook(bookTitle,authorId);
        List<Book> books = bookRepository.findBook(bookTitle);
        assertEquals(bookTitle, books.get(0).getBookTitle());
        assertEquals(authorId, books.get(0).getAuthorId());
        assertEquals(bookId, books.get(0).getId());
    }

// Добавил сравнение ответа из бд и book
    public static void bookCheckResponse(String bookTitle, Long authorId, Long bookId,Book book, String updated) {

        List<Book> DBbook = bookRepository.findBook(bookTitle);
        book.setBookTitle(bookTitle);
        book.setAuthorId(authorId);
        book.setId(bookId);
        book.setUpdated(updated);
        String bookWithoutMillis = book.toString().substring(0,book.toString().length() -4);
        String dbBookWithoutMillis = DBbook.get(0).toString().substring(0,DBbook.get(0).toString().length() -4);

        assertEquals(bookWithoutMillis, dbBookWithoutMillis);
        System.out.println(dbBookWithoutMillis);
        System.out.println(bookWithoutMillis);
    }

    public static void compareBookResponsePositive(BooksSaveResponse booksSaveResponse, String bookTitle) {
        List<Book> books = bookRepository.findBook(bookTitle);
        assertEquals(booksSaveResponse.getBookId(),books.get(0).getId());
    }

    public static void findBookNegative(String bookTitle) {
        List<Book> books = bookRepository.findBook(bookTitle);
        assertTrue(books.isEmpty());
    }

    public static void findBooksPositive() {
        List<Book> books = bookRepository.findAll();
        assertFalse(books.isEmpty());
    }
    }


