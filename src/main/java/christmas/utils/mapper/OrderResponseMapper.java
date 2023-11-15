package christmas.utils.mapper;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.dto.response.OrderItemResponse;
import christmas.dto.response.OrderResponse;
import java.util.ArrayList;
import java.util.List;

public class OrderResponseMapper {

    public static OrderResponse fromOrder(Order order) {
        return new OrderResponse(mapToOrderItemResponse(order));
    }

    private static List<OrderItemResponse> mapToOrderItemResponse(Order order) {
        List<OrderItemResponse> itemResponses = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            itemResponses.add(
                    new OrderItemResponse(orderItem.getMenuName(), orderItem.getCount(), orderItem.getPrice()));
        }
        return itemResponses;
    }
}
