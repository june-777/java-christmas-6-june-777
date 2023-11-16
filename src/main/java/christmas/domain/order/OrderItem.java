package christmas.domain.order;

import christmas.domain.Menu;
import christmas.domain.MenuType;
import christmas.domain.exception.MenuNotFoundException;
import christmas.domain.exception.OutOfRangeOrderItemCountException;

public class OrderItem {
    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 20;
    private final Menu menu;
    private final int count;

    public OrderItem(String menuName, int count) {
        this.menu = validate(menuName, count);
        this.count = count;
    }

    /**
     * @throws IllegalArgumentException:
     * @throws MenuNotFoundException:             없는 메뉴인 경우
     * @throws OutOfRangeOrderItemCountException: 수량이 1 ~ 20개가 아닌 경우
     */
    private Menu validate(String menuName, int count) {
        Menu menu = validateExistMenu(menuName);
        validateCount(count);
        return menu;
    }

    private Menu validateExistMenu(String menuName) {
        return Menu.find(menuName).orElseThrow(MenuNotFoundException::new);
    }

    private void validateCount(int count) {
        if (isOutOfRange(count)) {
            throw new OutOfRangeOrderItemCountException();
        }
    }

    private boolean isOutOfRange(int count) {
        return count < MIN_COUNT || count > MAX_COUNT;
    }

    public boolean isSameMenuType(MenuType menuType) {
        return menu.getMenuType() == menuType;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getPrice() {
        return menu.getPrice() * count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "(" + menu.getName() + "," + count + ")";
    }
}
