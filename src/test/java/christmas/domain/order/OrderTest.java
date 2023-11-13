package christmas.domain.order;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class OrderTest {

    private static final String TEST_HELPER_PATH = "christmas.helper.TestHelper";

    @Nested
    @DisplayName("총 주문 금액을 계산하는 기능 테스트")
    class TotalOrderPriceTest {

        @ParameterizedTest
        @CsvSource(value = {"양송이수프:1:6000", "타파스:2:11000", "시저샐러드:3:24000",
                "티본스테이크:4:220000", "바비큐립:20:1080000", "해산물파스타:19:665000", "크리스마스파스타:18:450000",
                "초코케이크:17:255000", "아이스크림:13:65000"}, delimiter = ':')
        @DisplayName("주문 메뉴 단 건 가격 테스트")
        void onePriceTest(String menuName, int count, int expectedPrice) {
            Order order = new Order(List.of(new OrderItem(menuName, count)));
            Assertions.assertThat(order.getTotalOrderPrice()).isEqualTo(expectedPrice);
        }

        @ParameterizedTest
        @MethodSource(TEST_HELPER_PATH + "#createSuccessOrder")
        @DisplayName("주문 메뉴 총 가격 테스트")
        void totalPriceTest(List<OrderItem> orderItems) {
            Order order = new Order(orderItems);
            int expectedPrice = 496500;
            Assertions.assertThat(order.getTotalOrderPrice()).isEqualTo(expectedPrice);
        }
    }
}