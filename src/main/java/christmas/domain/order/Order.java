package christmas.domain.order;

import java.util.List;

public class Order {
    private final List<OrderItem> order;

    public Order(List<OrderItem> order) {
        OrderValidator.validate(order);
        this.order = order;
    }
}
