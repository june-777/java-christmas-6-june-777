package christmas.service;

import christmas.dto.OrderMenu;
import christmas.service.exception.DuplicateMenuException;
import christmas.service.exception.ExceedTotalMenuCountException;
import christmas.service.exception.MenuNotFoundException;
import christmas.service.exception.OnlyBeverageMenuException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class OrderValidatorTest {

    private static final String TESTHELPER_PATH = "christmas.helper.TestHelper";
    OrderValidator orderValidator = new OrderValidator();

    @Nested
    @DisplayName("존재하지 않는 메뉴 주문 예외 테스트")
    class MenuNotFountExceptionTest {
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
        @DisplayName("[Exception] 존재하지 않는 메뉴이면 -> MenuNotFoundException이 발생한다. (메뉴 여러개)")
        void notExistMenuMany(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createNotExistAndDuplicateMenu")
        @DisplayName("[Exception] 존재하지 않는 메뉴 + 중복 메뉴이면 -> MenuNotFoundException이 발생한다.")
        void notExistMenuAndDuplicate(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createNotExistAndOnlyBeverageMenu")
        @DisplayName("[Exception] 존재하지 않는 메뉴 + 음료수만 주문이면 -> MenuNotFoundException이 발생한다.")
        void notExistAndOnlyBeverageMenu(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createNotExistAndExceedCount")
        @DisplayName("[Exception] 존재하지 않는 메뉴 + 주문 개수 초과이면 -> MenuNotFoundException이 발생한다.")
        void notExistAndExceedMenuCount(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createNotExistAndMix")
        @DisplayName("[Exception] 존재하지 않는 메뉴 + 중복 메뉴 + 음료수만 주문 + 주문 개수 초과이면 -> MenuNotFoundException이 발생한다.")
        void notExistTotalMix(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(MenuNotFoundException.class);
        }
    }

    @Nested
    @DisplayName("중복 메뉴 선택 주문 예외 테스트")
    class DuplicateMenuExceptionTest {
        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createDuplicate")
        @DisplayName("[Exception] 중복 메뉴 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicate(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createDuplicateAndOnlyBeverage")
        @DisplayName("[Exception] 중복 메뉴 + 음료수만 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicateAndOnlyBeverage(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createDuplicateAndExceedCount")
        @DisplayName("[Exception] 중복 메뉴 + 숫자 초과 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicateAndExceedCount(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createDuplicateAndMix")
        @DisplayName("[Exception] 중복 메뉴 + 음료수만 + 숫자 초과 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicateAndMix(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(DuplicateMenuException.class);
        }
    }

    @Nested
    @DisplayName("음료수 메뉴만 선택한 주문 예외 테스트")
    class OnlyBeverageMenuExceptionTest {

        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createOnlyBeverage")
        @DisplayName("[Exception] 음료수만 존재하면 AllMenuBeverageException이 발생한다.")
        void allMenuBeverage(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(OnlyBeverageMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createOnlyBeverageAndExceedCount")
        @DisplayName("[Exception] 음료수만 존재 + 주문 개수 초과하면 AllMenuBeverageException이 발생한다.")
        void allMenuBeverageAndExceedMenuCount(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(OnlyBeverageMenuException.class);
        }
    }

    @Nested
    @DisplayName("총 주문 개수 20개를 초과한 주문 예외 테스트")
    class ExceedTotalMenuCountExceptionTest {
        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createExceedCount")
        @DisplayName("[Exception] 주문 개수 초과하면 ExceedTotalMenuCountException 발생한다.")
        void exceedCount(List<OrderMenu> orderMenus) {
            Assertions.assertThatThrownBy(() -> orderValidator.validateOrder(orderMenus))
                    .isInstanceOf(ExceedTotalMenuCountException.class);
        }
    }

    @Nested
    @DisplayName("주문 성공 테스트")
    class SuccessOrderTest {
        @ParameterizedTest
        @MethodSource(TESTHELPER_PATH + "#createSuccessOrder")
        @DisplayName("[Success] 주문이 성공하면 예외가 발생하지 않는다.")
        void exceedCount(List<OrderMenu> orderMenus) {
            Assertions.assertThatCode(() -> orderValidator.validateOrder(orderMenus))
                    .doesNotThrowAnyException();
        }
    }
}