package christmas.service.event.discount.executor;

import christmas.domain.EventType;
import christmas.domain.MenuType;
import christmas.domain.order.Order;

public class WeekDayDiscountExecutor implements DiscountExecutor {

    public int execute(Order order) {
        return EventType.WEEKDAY.calculateDiscountAmount(order.getDay(),
                order.getSpecificMenuOrderCount(MenuType.DESSERT));
    }
}
