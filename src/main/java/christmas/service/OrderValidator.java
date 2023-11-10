package christmas.service;

import christmas.dto.OrderMenu;
import christmas.service.exception.MenuNotFoundException;
import christmas.utils.message.ExceptionMessage;
import java.util.List;

public class OrderValidator {

    /**
     * @throws IllegalArgumentException
     * @throws MenuNotFoundException:   없는 메뉴인 경우
     */
    public void validateOrder(List<OrderMenu> orderMenus) {
        validateExistMenu(orderMenus);
        // TODO: 음료만 주문했는지 검증
        // TODO: 총 주문 개수가 20개 초과인지 검증
    }

    private void validateExistMenu(List<OrderMenu> orderMenus) {
        for (OrderMenu orderMenu : orderMenus) {
            String orderMenuName = orderMenu.getMenuName();
            if (Menu.find(orderMenuName).isEmpty()) {
                throw new MenuNotFoundException(ExceptionMessage.MESSAGE.getError());
            }
        }
    }
}
