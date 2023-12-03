package academy.kata.repositoryClasses;

import academy.kata.entity.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static academy.kata.environments.LibraryDatabaseConfiguration.getSession;

public class BookRepository {

    Session session;

    public BookRepository() {
        session = getSession();
    }

    public List<Book> findAll() {
        final String hql = """
           SELECT * FROM book
                """;
        return session.createNativeQuery(hql, Book.class).getResultList();
    }

    public void deleteAll() {
        final String hql = """
           DELETE FROM book
           """;
        Transaction tr = session.beginTransaction();
        session.createNativeQuery(hql, Book.class).executeUpdate();tr.commit();
    }

    public void insertBook(String bookTitle, long authorId) {
        final String hql = """
           INSERT INTO book
           (book_title, author_id)
           VALUES(:bookTitle, :authorId)
           """;
        Transaction tr = session.beginTransaction();
        session.createNativeQuery(hql, Book.class).setParameter("bookTitle", bookTitle).setParameter("authorId", authorId).executeUpdate();
        tr.commit();
    }

    public List<Book> findBook(String bookTitle) {
        final String hql = """
           SELECT * FROM book WHERE book_title =:bookTitle
           """;
        return session.createNativeQuery(hql, Book.class).setParameter("bookTitle",bookTitle).getResultList();
}

    public void deleteOneBook(String bookTitle) {
        final String hql = """
           DELETE FROM book WHERE book_title =:bookTitle
           """;
        Transaction tr = session.beginTransaction();
        session.createNativeQuery(hql, Book.class).setParameter("bookTitle",bookTitle).executeUpdate();tr.commit();
    }
}

