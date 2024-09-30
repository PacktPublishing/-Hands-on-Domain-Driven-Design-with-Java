package expert.os.books.ddd.chapter04;

import java.util.Optional;

public interface Library {

    Book register(Book book);

    Optional<Book> findByTitle(String title);

    void unregister(Book book);
}
