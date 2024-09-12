package expert.os.books.ddd.chapter03;

import java.util.Optional;

public interface BookDAO {

    Optional<Book> findById(String id);

    void insert(Book book);

    void update(Book book);

    void deleteByTitle(String title);
}
