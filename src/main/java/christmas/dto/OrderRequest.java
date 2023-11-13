package christmas.dto;

import java.util.List;

public class OrderRequest {
    private final List<OrderItemRequest> orderItems;
    private final int orderDay;

    public OrderRequest(List<OrderItemRequest> orderItems, int orderDay) {
        this.orderItems = orderItems;
        this.orderDay = orderDay;
    }

    public List<OrderItemRequest> getOrderItems() {
        return orderItems;
    }

    public int getOrderDay() {
        return orderDay;
    }
}
