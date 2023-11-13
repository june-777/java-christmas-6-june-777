package christmas.service;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.dto.OrderItemRequest;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public Order createOrder(List<OrderItemRequest> orderItemRequests) {
        List<OrderItem> orderItems = createOrderItems(orderItemRequests);
        return new Order(orderItems);
    }

    private List<OrderItem> createOrderItems(List<OrderItemRequest> orderItemRequests) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest orderItemRequest : orderItemRequests) {
            OrderItem orderItem = new OrderItem(orderItemRequest.getMenuName(), orderItemRequest.getOrderCount());
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}


