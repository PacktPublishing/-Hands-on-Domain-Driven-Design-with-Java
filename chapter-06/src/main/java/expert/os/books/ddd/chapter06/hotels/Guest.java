package expert.os.books.ddd.chapter06.hotels;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Guest(String documentNumber, String name) {
}
