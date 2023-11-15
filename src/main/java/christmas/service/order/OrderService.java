package christmas.service.order;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.dto.request.OrderItemRequest;
import christmas.dto.request.OrderRequest;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public Order createOrder(final OrderRequest orderRequest) {
        List<OrderItem> orderItem = createOrderItems(orderRequest.getOrderItems());
        return new Order(orderItem, orderRequest.getOrderDay());
    }

    private List<OrderItem> createOrderItems(final List<OrderItemRequest> orderItemRequests) {
        List<OrderItem> OrderItems = new ArrayList<>();
        for (OrderItemRequest orderItemRequest : orderItemRequests) {
            OrderItem orderItem = new OrderItem(orderItemRequest.getMenuName(), orderItemRequest.getOrderCount());
            OrderItems.add(orderItem);
        }
        return OrderItems;
    }
}


