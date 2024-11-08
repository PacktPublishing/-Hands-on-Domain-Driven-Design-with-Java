package expert.os.books.ddd.chapter06.hotels;

import org.jmolecules.ddd.annotation.Service;

@Service
public interface PaymentService {

    void pay(Guest guest);
}
