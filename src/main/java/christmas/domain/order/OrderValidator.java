package christmas.domain.order;

import christmas.domain.Menu;
import christmas.domain.MenuType;
import christmas.domain.exception.DuplicateMenuException;
import christmas.domain.exception.ExceedTotalMenuCountException;
import christmas.domain.exception.OnlyBeverageMenuException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderValidator {

    private static final int TOTAL_ORDER_COUNT_LIMIT = 20;

    /**
     * @throws IllegalArgumentException:
     * @throws DuplicateMenuException:        중복 메뉴가 존재하는 경우
     * @throws OnlyBeverageMenuException:     음료수만 주문한 경우
     * @throws ExceedTotalMenuCountException: 주문 개수가 20개 초과인 경우
     */
    public static void validate(List<OrderItem> orderMenus) {
        validateDuplicateMenu(orderMenus);
        validateOnlyDrinkMenu(orderMenus);
        validateTotalOrderCount(orderMenus);
    }

    private static void validateDuplicateMenu(List<OrderItem> orderMenus) {
        Set<Menu> uniqueOrderMenus = createUniqueOrderMenus(orderMenus);

        if (orderMenus.size() != uniqueOrderMenus.size()) {
            throw new DuplicateMenuException();
        }
    }

    private static Set<Menu> createUniqueOrderMenus(List<OrderItem> orderMenus) {
        return orderMenus.stream()
                .map(OrderItem::getMenuName)
                .map(Menu::find)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    private static void validateOnlyDrinkMenu(List<OrderItem> orderMenus) {
        boolean allMenuBeverage = isAllMenuBeverage(orderMenus);

        if (allMenuBeverage) {
            throw new OnlyBeverageMenuException();
        }
    }

    private static boolean isAllMenuBeverage(List<OrderItem> orderMenus) {
        return orderMenus.stream()
                .map(OrderItem::getMenuName)
                .map(Menu::find)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Menu::getMenuType)
                .allMatch(menuType -> menuType == MenuType.BEVERAGE);
    }

    private static void validateTotalOrderCount(List<OrderItem> orderMenus) {
        int totalOrderCount = calculateTotalOrderCount(orderMenus);

        if (totalOrderCount > TOTAL_ORDER_COUNT_LIMIT) {
            throw new ExceedTotalMenuCountException();
        }
    }

    private static int calculateTotalOrderCount(List<OrderItem> orderMenus) {
        return orderMenus.stream()
                .map(OrderItem::getCount)
                .reduce(0, Integer::sum);
    }
}
