package christmas.service;

import christmas.dto.OrderMenu;
import christmas.service.exception.MenuNotFoundException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class OrderValidatorTest {

    private static final String TESTHELPER_PATH = "christmas.helper.TestHelper";
    OrderValidator orderValidator = new OrderValidator();

    @ParameterizedTest
    @CsvSource(value = {
            "양송이수우프,1", "타파스!,10", "시저샐러드ㅋ,3",
            "티본 스테이크,2", "바비큐Leap,5", "씨푸드파스타,7", "산타파스타,4",
            "쵸코케이크,1", "아이쓰크림,3",
            "콜라,12", "화이트와인,11", "샴패인,13"})
    @DisplayName("[Exception] 존재하지 않는 메뉴이면 MenuNotFoundException이 발생한다. (메뉴 1개)")
    void notExistMenuOne(String menuName, int orderCount) {
        OrderMenu orderMenu = new OrderMenu(menuName, orderCount);
        List<OrderMenu> orderMenus = List.of(orderMenu);

        Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                .isInstanceOf(MenuNotFoundException.class);
    }

    @ParameterizedTest
    @MethodSource(TESTHELPER_PATH + "#createOrderNotExistMenu")
    @DisplayName("[Exception] 존재하지 않는 메뉴이면 MenuNotFoundException이 발생한다. (메뉴 여러개)")
    void notExistMenuMany(List<OrderMenu> orderMenus) {
        Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                .isInstanceOf(MenuNotFoundException.class);
    }
}