package christmas.domain.order;

import christmas.domain.MenuType;
import java.util.Collections;
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

    public int getSpecificMenuOrderCount(MenuType menuType) {
        int count = 0;
        for (OrderItem orderItem : order) {
            if (orderItem.isSameMenuType(menuType)) {
                count += orderItem.getCount();
            }
        }
        return count;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(order);
    }

    @Override
    public String toString() {
        return "{" + order + "," + day + "}";
    }
}
