package christmas.dto.request;

public class OrderItemRequest {
    private final String menuName;
    private final int orderCount;

    public OrderItemRequest(String menuName, int orderCount) {
        this.menuName = menuName;
        this.orderCount = orderCount;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getOrderCount() {
        return orderCount;
    }

    @Override
    public String toString() {
        return "OrderItemRequest{" +
                "menuName='" + menuName + '\'' +
                ", orderCount=" + orderCount +
                '}';
    }
}
