package christmas.service;

import christmas.dto.OrderMenu;
import christmas.service.exception.DuplicateMenuException;
import christmas.service.exception.ExceedTotalMenuCountException;
import christmas.service.exception.MenuNotFoundException;
import christmas.service.exception.OnlyBeverageMenuException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderValidator {

    private final int TOTAL_ORDER_COUNT_LIMIT = 20;

    /**
     * @throws IllegalArgumentException:
     * @throws MenuNotFoundException:         없는 메뉴인 경우
     * @throws DuplicateMenuException:        중복 메뉴가 존재하는 경우
     * @throws OnlyBeverageMenuException:     음료수만 주문한 경우
     * @throws ExceedTotalMenuCountException: 주문 개수가 20개 초과인 경우
     */
    public void validateOrder(List<OrderMenu> orderMenus) {
        validateExistMenu(orderMenus);
        validateDuplicateMenu(orderMenus);
        validateOnlyDrinkMenu(orderMenus);
        validateTotalOrderCount(orderMenus);
    }

    private void validateExistMenu(List<OrderMenu> orderMenus) {
        for (OrderMenu orderMenu : orderMenus) {
            String orderMenuName = orderMenu.getMenuName();
            Menu.find(orderMenuName).orElseThrow(MenuNotFoundException::new);
        }
    }

    private void validateDuplicateMenu(List<OrderMenu> orderMenus) {
        Set<Menu> uniqueOrderMenus = createUniqueOrderMenus(orderMenus);

        if (orderMenus.size() != uniqueOrderMenus.size()) {
            throw new DuplicateMenuException();
        }
    }

    private static Set<Menu> createUniqueOrderMenus(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenu::getMenuName)
                .map(Menu::find)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    private void validateOnlyDrinkMenu(List<OrderMenu> orderMenus) {
        boolean allMenuBeverage = isAllMenuBeverage(orderMenus);

        if (allMenuBeverage) {
            throw new OnlyBeverageMenuException();
        }
    }

    private boolean isAllMenuBeverage(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenu::getMenuName)
                .map(Menu::find)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Menu::getMenuType)
                .allMatch(menuType -> menuType == MenuType.BEVERAGE);
    }

    private void validateTotalOrderCount(List<OrderMenu> orderMenus) {
        int totalOrderCount = calculateTotalOrderCount(orderMenus);

        if (totalOrderCount > TOTAL_ORDER_COUNT_LIMIT) {
            throw new ExceedTotalMenuCountException();
        }
    }

    private int calculateTotalOrderCount(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .map(OrderMenu::getOrderCount)
                .reduce(0, Integer::sum);
    }
}
