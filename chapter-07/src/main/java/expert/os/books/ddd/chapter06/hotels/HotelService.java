package expert.os.books.ddd.chapter06.hotels;

import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;

@ApplicationLayer
@Service
public class HotelService {

    private final Hotel hotel;

    private final PaymentService paymentService;

    public HotelService(Hotel hotel, PaymentService paymentService) {
        this.hotel = hotel;
        this.paymentService = paymentService;
    }

}
