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


