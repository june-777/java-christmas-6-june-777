package christmas.service;

import christmas.domain.exception.DuplicateMenuException;
import christmas.domain.exception.ExceedTotalMenuCountException;
import christmas.domain.exception.OnlyBeverageMenuException;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class OrderValidatorTest {

    private static final String ORDER_ITEMS_GENERATOR_PATH = "christmas.helper.OrderItemsGenerator";

    @Nested
    @DisplayName("중복 메뉴 선택 주문 예외 테스트")
    class DuplicateMenuExceptionTest {
        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createDuplicate")
        @DisplayName("[Exception] 중복 메뉴 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicate(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createDuplicateAndOnlyBeverage")
        @DisplayName("[Exception] 중복 메뉴 + 음료수만 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicateAndOnlyBeverage(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createDuplicateAndExceedCount")
        @DisplayName("[Exception] 중복 메뉴 + 총 주문 개수 초과 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicateAndExceedCount(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createDuplicateAndMix")
        @DisplayName("[Exception] 중복 메뉴 + 음료수만 + 총 주문 개수 초과 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicateAndMix(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(DuplicateMenuException.class);
        }
    }

    @Nested
    @DisplayName("음료수 메뉴만 선택한 주문 예외 테스트")
    class OnlyBeverageMenuExceptionTest {

        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createOnlyBeverage")
        @DisplayName("[Exception] 음료수만 존재하면 AllMenuBeverageException이 발생한다.")
        void allMenuBeverage(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(OnlyBeverageMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createOnlyBeverageAndExceedCount")
        @DisplayName("[Exception] 음료수만 존재 + 총 주문 개수 초과하면 AllMenuBeverageException이 발생한다.")
        void allMenuBeverageAndExceedMenuCount(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(OnlyBeverageMenuException.class);
        }
    }

    @Nested
    @DisplayName("총 주문 개수 20개를 초과한 주문 예외 테스트")
    class ExceedTotalMenuCountExceptionTest {
        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createExceedCount")
        @DisplayName("[Exception] 총 주문 개수 초과하면 ExceedTotalMenuCountException 발생한다.")
        void exceedCount(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems))
                    .isInstanceOf(ExceedTotalMenuCountException.class);
        }
    }

    @Nested
    @DisplayName("주문 성공 테스트")
    class SuccessOrderTest {
        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createSuccessOrder")
        @DisplayName("[Success] 주문이 성공하면 예외가 발생하지 않는다.")
        void exceedCount(List<OrderItem> orderItems) {
            Assertions.assertThatCode(() -> new Order(orderItems))
                    .doesNotThrowAnyException();
        }
    }
}