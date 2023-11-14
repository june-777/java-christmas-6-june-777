package christmas.service.event.discount.executor;

import christmas.domain.EventType;
import christmas.domain.MenuType;
import christmas.domain.order.Order;

public class WeekEndDiscountExecutor implements DiscountExecutor {

    public int execute(Order order) {
        return EventType.WEEKEND.calculateDiscountAmount(order.getDay(),
                order.getSpecificMenuOrderCount(MenuType.MAIN_COURSE));
    }
}
