package expert.os.books.ddd.chapter06.hotels.infra;

import expert.os.books.ddd.chapter06.hotels.Guest;
import expert.os.books.ddd.chapter06.hotels.PaymentService;

import java.util.logging.Logger;

public class CreditCard implements PaymentService {

    private static final Logger LOGGER = Logger.getLogger(CreditCard.class.getName());

    @Override
    public void pay(Guest guest) {
        LOGGER.info("Payment with credit card: " + guest);
    }
}
