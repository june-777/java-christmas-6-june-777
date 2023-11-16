package christmas.service;

import christmas.domain.exception.DuplicateMenuException;
import christmas.domain.exception.ExceedTotalMenuCountException;
import christmas.domain.exception.MenuNotFoundException;
import christmas.domain.exception.OnlyBeverageMenuException;
import christmas.domain.exception.OutOfRangeOrderItemCountException;
import christmas.dto.request.OrderRequest;
import christmas.service.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class OrderServiceTest {

    private static final String ORDER_REQUEST_GENERATOR_PATH = "christmas.helper.OrderRequestGenerator";
    OrderService orderService = new OrderService();

    @Nested
    @DisplayName("주문 실패 테스트")
    class FailOrderTest {

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createOrderNotExistMenu")
        @DisplayName("[Exception] 존재하지 않는 메뉴이면 -> MenuNotFoundException이 발생한다.")
        void notExistMenuMany(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createNotExistAndDuplicateMenu")
        @DisplayName("[Exception] 존재하지 않는 메뉴 + 중복 메뉴이면 -> MenuNotFoundException이 발생한다.")
        void notExistMenuAndDuplicate(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createNotExistAndOnlyBeverageMenu")
        @DisplayName("[Exception] 존재하지 않는 메뉴 + 음료수만 주문이면 -> MenuNotFoundException이 발생한다.")
        void notExistAndOnlyBeverageMenu(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createNotExistAndExceedCount")
        @DisplayName("[Exception] 존재하지 않는 메뉴 + 주문 개수 초과이면 -> MenuNotFoundException이 발생한다.")
        void notExistAndExceedMenuCount(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createNotExistAndMix")
        @DisplayName("[Exception] 존재하지 않는 메뉴 + 중복 메뉴 + 음료수만 주문 + 주문 개수 초과이면 -> MenuNotFoundException이 발생한다.")
        void notExistTotalMix(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createOutOfRangeLessMin")
        @DisplayName("[Exception] 단 건 주문 상품 개수가 1개 미만이면 -> OutOfRangeOrderItemCountException 발생한다.")
        void outOfRangeLessMin(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(OutOfRangeOrderItemCountException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createOutOfRangeLessMinAndDuplicate")
        @DisplayName("[Exception] 단 건 주문 상품 개수가 1개 미만 + 중복 메뉴이면 -> OutOfRangeOrderItemCountException 발생한다.")
        void outOfRangeLessMinAndDuplicate(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(OutOfRangeOrderItemCountException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createOutOfRangeGreaterMax")
        @DisplayName("[Exception] 단 건 주문 상품 개수가 20개 초과이면 -> OutOfRangeOrderItemCountException 발생한다.")
        void outOfRangeGreaterMax(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(OutOfRangeOrderItemCountException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createOutOfRangeGreaterMaxAndOnlyBeverage")
        @DisplayName("[Exception] 단 건 주문 상품 개수가 20개 초과 + 음료수만 주문이면 -> OutOfRangeOrderItemCountException 발생한다.")
        void outOfRangeGreaterMaxAndOnlyBeverage(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(OutOfRangeOrderItemCountException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createDuplicate")
        @DisplayName("[Exception] 중복 메뉴 주문이면 -> DuplicateMenuException이 발생한다.")
        void duplicate(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createOnlyBeverage")
        @DisplayName("[Exception] 음료수 메뉴만 주문이면 -> OnlyBeverageMenuException이 발생한다.")
        void onlyBeverage(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(OnlyBeverageMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createExceedTotalOrderCount")
        @DisplayName("[Exception] 총 주문 개수가 20개 초과이면 -> ExceedTotalMenuCountException 발생한다.")
        void exceedTotalOrderCount(OrderRequest orderMenus) {
            Assertions.assertThatThrownBy(() -> orderService.createOrder(orderMenus))
                    .isInstanceOf(ExceedTotalMenuCountException.class);
        }
    }

    @Nested
    @DisplayName("주문 성공 테스트")
    class SuccessOrderTest {
        @ParameterizedTest
        @MethodSource(ORDER_REQUEST_GENERATOR_PATH + "#createSuccess")
        @DisplayName("[Success] 올바른 주문이면 -> 예외가 발생하지 않는다.")
        void exceedTotalOrderCount(OrderRequest orderMenus) {
            Assertions.assertThatCode(() -> orderService.createOrder(orderMenus))
                    .doesNotThrowAnyException();
        }
    }
}