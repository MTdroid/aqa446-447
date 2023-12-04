package unit;

import academy.kata.entity.Book;
import academy.kata.models.authorsSave.response.AuthorsSaveResponse;
import academy.kata.repositoryClasses.BookRepository;
import academy.kata.rest.PositiveRequestSpecification;
import academy.kata.steps.checkResponse.HibernateDbCheck;
import academy.kata.utils.TestDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookTableTest {
    String firstName = TestDataGenerator.generateData();
    String familyName=TestDataGenerator.generateData();
    String secondName=TestDataGenerator.generateData();
    String date = TestDataGenerator.generateDate();

    @Test
    @DisplayName("Проверка работы с базой данных через Hibernate")
    public void hibernateInteractionWithDb(){

        AuthorsSaveResponse author = PositiveRequestSpecification.authorsSaveResponse(firstName,familyName,secondName, date,201);

        BookRepository bookRepository = new BookRepository();

        bookRepository.deleteAll();
        bookRepository.insertBook("Book1",author.getAuthorId());
        bookRepository.insertBook("Book2",author.getAuthorId());

        List<Book> books = bookRepository.findAll();
        HibernateDbCheck.checkListSize(books,2);
        System.out.println(books);

        List<Book> book = bookRepository.findBook("Book1");
        HibernateDbCheck.checkListSize(book,1);
        System.out.println(book);

        bookRepository.deleteOneBook("Book2");

        List<Book> booksAfterRemove = bookRepository.findAll();
        HibernateDbCheck.checkListSize(booksAfterRemove,1);
        System.out.println(booksAfterRemove);
    }
}
