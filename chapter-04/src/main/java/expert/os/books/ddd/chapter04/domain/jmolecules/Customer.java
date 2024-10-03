package expert.os.books.ddd.chapter04.domain.jmolecules;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Customer(String name, String email) {

}
