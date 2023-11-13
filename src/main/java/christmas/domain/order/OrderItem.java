package christmas.domain.order;

import christmas.domain.Menu;
import christmas.domain.exception.MenuNotFoundException;

public class OrderItem {
    private final Menu menu;
    private final int count;

    public OrderItem(String menuName, int count) {
        this.menu = validate(menuName, count);
        this.count = count;
    }

    /**
     * @throws IllegalArgumentException:
     * @throws MenuNotFoundException:    없는 메뉴인 경우
     */
    private Menu validate(String menuName, int count) {
        Menu menu = validateExistMenu(menuName);
        return menu;
    }

    private Menu validateExistMenu(String menuName) {
        return Menu.find(menuName).orElseThrow(MenuNotFoundException::new);
    }
}
