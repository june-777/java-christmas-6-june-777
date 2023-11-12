package christmas.dto;

public class OrderMenu {
    private final String menuName;
    private final int orderCount;

    public OrderMenu(String menuName, int orderCount) {
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
        return "OrderMenu{" +
                "menuName='" + menuName + '\'' +
                ", orderCount=" + orderCount +
                '}';
    }
}
