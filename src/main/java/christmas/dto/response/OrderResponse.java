package christmas.dto.response;

import java.util.List;

public class OrderResponse {
    private final List<OrderItemResponse> orderItemResponses;

    public OrderResponse(List<OrderItemResponse> orderItemResponses) {
        this.orderItemResponses = orderItemResponses;
    }

    public List<OrderItemResponse> getOrderItemResponses() {
        return orderItemResponses;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItemResponse orderItemResponse : orderItemResponses) {
            totalPrice += orderItemResponse.getPrice();
        }
        return totalPrice;
    }
}
