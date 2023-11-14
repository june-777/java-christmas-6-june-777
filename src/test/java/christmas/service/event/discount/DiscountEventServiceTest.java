package christmas.service.event.discount;

import christmas.domain.EventType;
import christmas.domain.event.DiscountInformation;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DiscountEventServiceTest {

    DiscountEventService discountEventService = new DiscountEventService();

    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28})
    @DisplayName("평일 이벤트만 적용")
    void test1(int day) {
        // given
        Order order = createOrder(day);
        int expectedDiscountAmount = -14161;
        // when
        DiscountInformation discountInformation = discountEventService.calculateDiscountEvent(order);
        // then
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKDAY))
                .isEqualTo(expectedDiscountAmount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKEND))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.SPECIAL))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.CHRISTMAS))
                .isZero();
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7,
            11, 12, 13, 14,
            18, 19, 20, 21})
    @DisplayName("평일 + 크리스마스 이벤트 적용")
    void test2(int day) {
        // given
        Order order = createOrder(day);
        int expectedWeekDayDiscount = -14161;
        int expectedChristmasDiscount = EventType.CHRISTMAS.calculateDiscountAmount(day, day);
        int expectedTotalDiscount = expectedWeekDayDiscount + expectedChristmasDiscount;
        // when
        DiscountInformation discountInformation = discountEventService.calculateDiscountEvent(order);
        // then
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKDAY))
                .isEqualTo(expectedWeekDayDiscount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.CHRISTMAS))
                .isEqualTo(expectedChristmasDiscount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKEND))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.SPECIAL))
                .isZero();
        Assertions.assertThat(discountInformation.getTotalDiscountAmount()).isEqualTo(expectedTotalDiscount);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25})
    @DisplayName("평일 + 크리스마스 + 특별일 이벤트 적용")
    void test3(int day) {
        // given
        Order order = createOrder(day);
        int expectedWeekDayDiscount = -14161;
        int expectedChristmasDiscount = EventType.CHRISTMAS.calculateDiscountAmount(day, day);
        int expectedSpecialDayDiscount = -1000;
        int expectedTotalDiscount = expectedWeekDayDiscount + expectedChristmasDiscount + expectedSpecialDayDiscount;
        // when
        DiscountInformation discountInformation = discountEventService.calculateDiscountEvent(order);
        // then
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKDAY))
                .isEqualTo(expectedWeekDayDiscount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.CHRISTMAS))
                .isEqualTo(expectedChristmasDiscount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.SPECIAL))
                .isEqualTo(expectedSpecialDayDiscount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKEND))
                .isZero();
        Assertions.assertThat(discountInformation.getTotalDiscountAmount()).isEqualTo(expectedTotalDiscount);
    }

    @ParameterizedTest
    @ValueSource(ints = {31})
    @DisplayName("평일 + 특별일 이벤트 적용")
    void test4(int day) {
        // given
        Order order = createOrder(day);
        int expectedWeekDayDiscount = -14161;
        int expectedSpecialDayDiscount = -1000;
        int expectedTotalDiscount = expectedWeekDayDiscount + expectedSpecialDayDiscount;
        // when
        DiscountInformation discountInformation = discountEventService.calculateDiscountEvent(order);
        // then
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKDAY))
                .isEqualTo(expectedWeekDayDiscount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.SPECIAL))
                .isEqualTo(expectedSpecialDayDiscount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.CHRISTMAS))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKEND))
                .isZero();
        Assertions.assertThat(discountInformation.getTotalDiscountAmount()).isEqualTo(expectedTotalDiscount);
    }

    @ParameterizedTest
    @ValueSource(ints = {29, 30})
    @DisplayName("주말 이벤트만 적용")
    void test5(int day) {
        // given
        Order order = createOrder(day);
        int expectedWeekendDiscount = -18207;
        // when
        DiscountInformation discountInformation = discountEventService.calculateDiscountEvent(order);
        // then
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKEND))
                .isEqualTo(expectedWeekendDiscount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKDAY))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.SPECIAL))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.CHRISTMAS))
                .isZero();
        Assertions.assertThat(discountInformation.getTotalDiscountAmount()).isEqualTo(expectedWeekendDiscount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23})
    @DisplayName("주말 이벤트 + 크리스마스 적용")
    void test6(int day) {
        // given
        Order order = createOrder(day);
        int expectedWeekendDiscount = -18207;
        int expectedChristmasAmount = EventType.CHRISTMAS.calculateDiscountAmount(day, day);
        int expectedTotalDiscount = expectedWeekendDiscount + expectedChristmasAmount;
        // when
        DiscountInformation discountInformation = discountEventService.calculateDiscountEvent(order);
        // then
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKEND))
                .isEqualTo(expectedWeekendDiscount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.CHRISTMAS))
                .isEqualTo(expectedChristmasAmount);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKDAY))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.SPECIAL))
                .isZero();
        Assertions.assertThat(discountInformation.getTotalDiscountAmount()).isEqualTo(expectedTotalDiscount);
    }

    @Test
    @DisplayName("타파스 1개, 제로콜라 1개, 26일")
    void temp() {
        // given
        Order order = new Order(List.of(new OrderItem("타파스", 1), new OrderItem("제로콜라", 1)), 26);
        // when
        DiscountInformation discountInformation = discountEventService.calculateDiscountEvent(order);
        // then
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKEND))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.CHRISTMAS))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKDAY))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.SPECIAL))
                .isZero();
        Assertions.assertThat(discountInformation.getTotalDiscountAmount()).isZero();
    }

    @Test
    @DisplayName("티본스테이크 1개, 바비큐립 1개, 초코케이크 2개, 제로콜라 1개, 3일")
    void temp2() {
        // given
        Order order = new Order(List.of(new OrderItem("티본스테이크", 1), new OrderItem("바비큐립", 1),
                new OrderItem("초코케이크", 2), new OrderItem("제로콜라", 1)), 3);
        int expectedTotalDiscount = -6246;
        // when
        DiscountInformation discountInformation = discountEventService.calculateDiscountEvent(order);
        // then
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.CHRISTMAS))
                .isEqualTo(-1200);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKDAY))
                .isEqualTo(-4046);
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.WEEKEND))
                .isZero();
        Assertions.assertThat(discountInformation.getDiscountAmount(EventType.SPECIAL))
                .isEqualTo(-1000);
        Assertions.assertThat(discountInformation.getTotalDiscountAmount()).isEqualTo(expectedTotalDiscount);
    }

    private static Order createOrder(int day) {
        return new Order(List.of(new OrderItem("양송이수프", 1), new OrderItem("타파스", 1),
                new OrderItem("바비큐립", 3), new OrderItem("크리스마스파스타", 6),
                new OrderItem("초코케이크", 2), new OrderItem("아이스크림", 5),
                new OrderItem("제로콜라", 1), new OrderItem("레드와인", 1)), day);
    }
}