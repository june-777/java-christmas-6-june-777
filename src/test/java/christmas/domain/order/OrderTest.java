package christmas.domain.order;

import christmas.domain.MenuType;
import christmas.domain.exception.DuplicateMenuException;
import christmas.domain.exception.ExceedTotalMenuCountException;
import christmas.domain.exception.OnlyBeverageMenuException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class OrderTest {

    private static final String ORDER_ITEMS_GENERATOR_PATH = "christmas.helper.OrderItemsGenerator";

    @Nested
    @DisplayName("중복 메뉴 선택 주문 예외 테스트")
    class DuplicateMenuExceptionTest {
        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createDuplicate")
        @DisplayName("[Exception] 중복 메뉴 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicate(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems, 1))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createDuplicateAndOnlyBeverage")
        @DisplayName("[Exception] 중복 메뉴 + 음료수만 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicateAndOnlyBeverage(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems, 31))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createDuplicateAndExceedCount")
        @DisplayName("[Exception] 중복 메뉴 + 총 주문 개수 초과 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicateAndExceedCount(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems, 2))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createDuplicateAndMix")
        @DisplayName("[Exception] 중복 메뉴 + 음료수만 + 총 주문 개수 초과 주문이면 -> DuplicateMenuException 발생한다.")
        void duplicateAndMix(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems, 3))
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
            Assertions.assertThatThrownBy(() -> new Order(orderItems, 12))
                    .isInstanceOf(OnlyBeverageMenuException.class);
        }

        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createOnlyBeverageAndExceedCount")
        @DisplayName("[Exception] 음료수만 존재 + 총 주문 개수 초과하면 AllMenuBeverageException이 발생한다.")
        void allMenuBeverageAndExceedMenuCount(List<OrderItem> orderItems) {
            Assertions.assertThatThrownBy(() -> new Order(orderItems, 21))
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
            Assertions.assertThatThrownBy(() -> new Order(orderItems, 30))
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
            Assertions.assertThatCode(() -> new Order(orderItems, 29))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("총 주문 금액을 계산하는 기능 테스트")
    class TotalOrderPriceTest {

        @ParameterizedTest
        @CsvSource(value = {"양송이수프:1:6000", "타파스:2:11000", "시저샐러드:3:24000",
                "티본스테이크:4:220000", "바비큐립:20:1080000", "해산물파스타:19:665000", "크리스마스파스타:18:450000",
                "초코케이크:17:255000", "아이스크림:13:65000"}, delimiter = ':')
        @DisplayName("주문 메뉴 단 건 가격 테스트")
        void onePriceTest(String menuName, int count, int expectedPrice) {
            Order order = new Order(List.of(new OrderItem(menuName, count)), 18);
            Assertions.assertThat(order.getTotalOrderPrice()).isEqualTo(expectedPrice);
        }

        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createSuccessOrder")
        @DisplayName("주문 메뉴 총 가격 테스트")
        void totalPriceTest(List<OrderItem> orderItem) {
            Order order = new Order(orderItem, 12);
            int expectedPrice = 496500;
            Assertions.assertThat(order.getTotalOrderPrice()).isEqualTo(expectedPrice);
        }

        @Test
        void temp() {
            // given
            List<OrderItem> orderItems = List.of(new OrderItem("양송이수프", 1),
                    new OrderItem("티본스테이크", 1),
                    new OrderItem("초코케이크", 11), new OrderItem("아이스크림", 5),
                    new OrderItem("제로콜라", 1));
            Order order = new Order(orderItems, 3);
            // when
            int menuTypeCount = order.getSpecificMenuOrderCount(MenuType.DESSERT);
            // then
            Assertions.assertThat(menuTypeCount).isEqualTo(16);
        }
    }
}