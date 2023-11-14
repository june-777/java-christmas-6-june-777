package christmas.domain;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class EventTypeTest {

    @Nested
    @DisplayName("크리스마스 할인 이벤트를 적용하려는데")
    class ChristmasDiscountTest {

        @DisplayName("적용 가능한 날짜는 날짜만큼 할인된다.")
        @ParameterizedTest
        @CsvSource(value = {"1:-1000", "2:-1100", "3:-1200", "4:-1300", "5:-1400", "6:-1500", "7:-1600", "8:-1700",
                "9:-1800", "10:-1900", "11:-2000", "12:-2100", "13:-2200", "14:-2300", "15:-2400", "16:-2500",
                "17:-2600", "18:-2700", "19:-2800", "20:-2900", "21:-3000", "22:-3100", "23:-3200", "24:-3300",
                "25:-3400", "26:0", "27:0", "28:0", "29:0", "30:0", "31:0"}, delimiter = ':')
        void christmasDay(int day, int expectedDiscountAmount) {
            String menuForm = "양송이수프-1,바비큐립-2,초코케이크-3,샴페인-4";
            Order order = createOrder(day,
                    new OrderItem("양송이수프", 1), new OrderItem("바비큐립", 2),
                    new OrderItem("초코케이크", 3), new OrderItem("샴페인", 4));
            EventType eventType = EventType.CHRISTMAS;
            int resultDiscountAmount = eventType.calculateDiscountAmount(order.getDay(), order.getDay());
            Assertions.assertThat(resultDiscountAmount).isEqualTo(expectedDiscountAmount);
        }

        @DisplayName("적용 불가한 날짜는 0원이 할인된다.")
        @ParameterizedTest
        @CsvSource(value = {"26:0", "27:0", "28:0", "29:0", "30:0", "31:0"}, delimiter = ':')
        void notChristmasDay(int day, int expectedDiscountAmount) {
            String menuForm = "양송이수프-1,바비큐립-2,초코케이크-3,샴페인-4";
            Order order = createOrder(day,
                    new OrderItem("양송이수프", 1), new OrderItem("바비큐립", 2),
                    new OrderItem("초코케이크", 3), new OrderItem("샴페인", 4));
            EventType eventType = EventType.CHRISTMAS;
            int resultDiscountAmount = eventType.calculateDiscountAmount(order.getDay(), order.getDay());
            Assertions.assertThat(resultDiscountAmount).isEqualTo(expectedDiscountAmount);
        }

        private static Order createOrder(int day, OrderItem... orderItems) {
            return new Order(List.of(orderItems), day);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @DisplayName("할인 가능 이벤트 - 주말")
    void weekend(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKEND);
    }

    @ParameterizedTest
    @ValueSource(ints = {29, 30})
    @DisplayName("할인 가능 이벤트 - 주말만")
    void onlyWeekend(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(1);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKEND);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23})
    @DisplayName("할인 가능 이벤트 - 주말, 크리스마스")
    void weekendAndChristmas(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(2);
        Assertions.assertThat(applicableEventTypes).contains(EventType.CHRISTMAS);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKEND);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7,
            10, 11, 12, 13, 14,
            17, 18, 19, 20, 21,
            24, 25, 26, 27, 28,
            31})
    @DisplayName("할인 가능 이벤트 - 평일")
    void weekDay(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKDAY);
    }

    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28})
    @DisplayName("할인 가능 이벤트 - 평일만")
    void onlyWeekDay(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(1);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKDAY);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7,
            11, 12, 13, 14,
            18, 19, 20, 21})
    @DisplayName("할인 가능 이벤트 - 평일, 크리스마스")
    void weekDayAndChristmas(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(2);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKDAY);
        Assertions.assertThat(applicableEventTypes).contains(EventType.CHRISTMAS);
    }

    @ParameterizedTest
    @ValueSource(ints = {31})
    @DisplayName("할인 가능 이벤트 - 평일, 특별")
    void weekDayAndSpecial(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(2);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKDAY);
        Assertions.assertThat(applicableEventTypes).contains(EventType.SPECIAL);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25})
    @DisplayName("할인 가능 이벤트 - 평일, 특별, 크리스마스")
    void weekDayAndSpecialAndChristmas(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(3);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKDAY);
        Assertions.assertThat(applicableEventTypes).contains(EventType.SPECIAL);
        Assertions.assertThat(applicableEventTypes).contains(EventType.CHRISTMAS);
    }
}