package expert.os.books.ddd.chapter05.hotels;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Guest(String documentNumber, String name) {
}
