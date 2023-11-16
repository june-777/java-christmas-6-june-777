package christmas.service.event.discount.executor;

import christmas.domain.order.Order;

public interface DiscountExecutor {
    int execute(Order order);
}
