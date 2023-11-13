package christmas.domain.order;

import java.util.List;

public class Order {
    private final List<OrderItem> order;
    private final OrderDay day;

    public Order(List<OrderItem> order, int day) {
        OrderValidator.validate(order);
        this.order = order;
        this.day = new OrderDay(day);
    }

    public int getTotalOrderPrice() {
        int sum = 0;
        for (OrderItem orderItem : order) {
            sum += orderItem.getPrice();
        }
        return sum;
    }

    public int getDay() {
        return day.getDay();
    }
}
