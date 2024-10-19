package expert.os.books.ddd.chapter04.hotels;

import org.jmolecules.ddd.annotation.Service;

@Service
public interface PaymentService {

    void pay(Guest guest);
}
