package christmas.service.event.discount.executor;

import christmas.domain.EventType;
import christmas.domain.order.Order;

public class ChristmasDiscountExecutor implements DiscountExecutor {

    public int execute(Order order) {
        return EventType.CHRISTMAS.calculateDiscountAmount(order.getDay(), order.getDay());
    }
}
