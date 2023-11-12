package christmas.service;

import christmas.dto.OrderMenu;
import christmas.service.exception.DuplicateMenuException;
import christmas.service.exception.MenuNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderValidator {

    /**
     * @throws IllegalArgumentException
     * @throws MenuNotFoundException:   없는 메뉴인 경우
     * @throws DuplicateMenuException:  중복 메뉴가 존재하는 경우
     */
    public void validateOrder(List<OrderMenu> orderMenus) {
        validateExistMenu(orderMenus);
        // TODO: 중복 메뉴 존재여부 검증
        validateDuplicateMenu(orderMenus);
        // TODO: 음료만 주문했는지 검증
        // TODO: 총 주문 개수가 20개 초과인지 검증
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
}
