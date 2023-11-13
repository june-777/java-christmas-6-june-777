package christmas.service;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.dto.OrderItemRequest;
import christmas.dto.OrderRequest;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public Order createOrder(OrderRequest orderRequest) {
        List<OrderItem> orderItem = createOrderItems(orderRequest.getOrderItems());
        return new Order(orderItem, orderRequest.getOrderDay());
    }

    private List<OrderItem> createOrderItems(List<OrderItemRequest> orderItemRequests) {
        List<OrderItem> OrderItems = new ArrayList<>();
        for (OrderItemRequest orderItemRequest : orderItemRequests) {
            OrderItem orderItem = new OrderItem(orderItemRequest.getMenuName(), orderItemRequest.getOrderCount());
            OrderItems.add(orderItem);
        }
        return OrderItems;
    }
}


