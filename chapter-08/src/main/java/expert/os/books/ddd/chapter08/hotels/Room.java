package expert.os.books.ddd.chapter08.hotels;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;


@Entity
public record Room (@Id String number, @Column Guest guest) {

}
