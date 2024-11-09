package org.soujava.samples.hotel;

import jakarta.nosql.Column;
import jakarta.nosql.Embeddable;
import jakarta.nosql.Entity;

@Embeddable(Embeddable.EmbeddableType.GROUPING)
public record Guest (@Column String documentNumber, @Column String name) {
}
