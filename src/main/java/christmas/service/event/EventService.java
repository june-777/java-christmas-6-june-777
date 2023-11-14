package christmas.service.event;

import christmas.domain.order.Order;
import christmas.dto.EventResultResponse;
import java.util.Optional;

public class EventService {

    private static final int MIN_TOTAL_PRICE = 10000;

    public Optional<EventResultResponse> applyEvent(Order order) {
        if (cantApplyEvent(order)) {
            return Optional.empty();
        }
        // TODO:
        return null;
    }

    private boolean cantApplyEvent(Order order) {
        return order.getTotalOrderPrice() < MIN_TOTAL_PRICE;
    }
}
