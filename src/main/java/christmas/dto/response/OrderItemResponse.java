package christmas.dto.response;

public class OrderItemResponse {
    private final String menuName;
    private final int count;
    private final int price;

    public OrderItemResponse(String menuName, int count, int price) {
        this.menuName = menuName;
        this.count = count;
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }
}
