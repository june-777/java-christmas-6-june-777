package christmas.service.event.discount.executor;

import christmas.domain.EventType;
import christmas.domain.order.Order;

public class SpecialDayDiscountExecutor implements DiscountExecutor {
    
    public int execute(Order order) {
        return EventType.SPECIAL.calculateDiscountAmount(order.getDay(), 0);
    }
}
