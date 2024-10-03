package expert.os.books.ddd.chapter04.domain.jmolecules;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public enum CardStatus {
    ACTIVE,
    INACTIVE,
    SUSPENDED;
}
