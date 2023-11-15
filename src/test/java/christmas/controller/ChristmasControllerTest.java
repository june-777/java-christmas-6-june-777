package christmas.controller;

import christmas.domain.EventType;
import christmas.domain.exception.DuplicateMenuException;
import christmas.domain.exception.ExceedTotalMenuCountException;
import christmas.domain.exception.MenuNotFoundException;
import christmas.domain.exception.OnlyBeverageMenuException;
import christmas.domain.exception.OutOfRangeOrderItemCountException;
import christmas.domain.order.Order;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.DiscountResponse;
import christmas.dto.response.FreeGiftResponse;
import christmas.service.badge.BadgeService;
import christmas.service.event.EventService;
import christmas.service.event.discount.DiscountEventService;
import christmas.service.event.gift.FreeGiftEventService;
import christmas.service.order.OrderService;
import christmas.utils.mapper.OrderRequestMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ChristmasControllerTest {

    OrderService orderService;
    EventService eventService;
    BadgeService badgeService;
    ChristmasController christmasController;

    @BeforeEach
    void beforeEach() {
        orderService = new OrderService();
        eventService = new EventService(new DiscountEventService(), new FreeGiftEventService());
        badgeService = new BadgeService();
        christmasController = new ChristmasController(orderService, eventService, badgeService);
    }

    @Nested
    @DisplayName("주문을 생성할 때")
    class OrderTest {

        @DisplayName("[Exception] 메뉴판에 없는 메뉴이면 -> MenuNotFoundException이 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"타파스-1, 제로콜라-2", "타파스 -1,제로콜라-2", "타파쓰-3"})
        void notExistMenu(String menuForm) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, 3);
            Assertions.assertThatThrownBy(() -> christmasController.createOrder(orderRequest))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @DisplayName("[Exception] 메뉴의 총 개수가 20개가 초과한다면 -> ExceedTotalMenuCountException 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"타파스-20,제로콜라-5", "타파스-1,제로콜라-20"})
        void exceedTotalMenuCount(String menuForm) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, 3);
            Assertions.assertThatThrownBy(() -> christmasController.createOrder(orderRequest))
                    .isInstanceOf(ExceedTotalMenuCountException.class);
        }

        @DisplayName("[Exception] 단건 메뉴의 개수가 1개 ~ 20개가 아니라면 -> OutOfRangeOrderItemCountException 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"타파스-0", "타파스-21", "바비큐립-5,타파스-21"})
        void outOfRangeOrderItemCount(String menuForm) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, 3);
            Assertions.assertThatThrownBy(() -> christmasController.createOrder(orderRequest))
                    .isInstanceOf(OutOfRangeOrderItemCountException.class);
        }

        @DisplayName("[Exception] 중복 메뉴가 존재한다면 -> DuplicateMenuException이 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"타파스-1,타파스-2", "바비큐립-5,아이스크림-1,바비큐립-1", "바비큐립-5,타파스-4,타파스-2"})
        void duplicateExist(String menuForm) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, 3);
            Assertions.assertThatThrownBy(() -> christmasController.createOrder(orderRequest))
                    .isInstanceOf(DuplicateMenuException.class);
        }

        @DisplayName("[Exception] 음료만 주문한다면 -> OnlyBeverageException이 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"제로콜라-1,레드와인-2", "샴페인-5,제로콜라-1,레드와인-1", "샴페인-2", "제로콜라-1", "레드와인-3"})
        void onlyBeverage(String menuForm) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, 3);
            Assertions.assertThatThrownBy(() -> christmasController.createOrder(orderRequest))
                    .isInstanceOf(OnlyBeverageMenuException.class);
        }
    }

    @Nested
    @DisplayName("이벤트를 적용했을 때")
    class EventTest {

        @DisplayName("증정 메뉴만 존재하면, 할인 내역 DTO는 비어있고 증정 내용 DTO는 내용이 있다.")
        @ParameterizedTest
        @CsvSource(value = {"티본스테이크-3:26", "바비큐립-3:27", "해산물파스타-4:28",
                "시저샐러드-15:29", "초코케이크-10:30"}, delimiter = ':')
        void onlyFreeGift(String menuForm, int day) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = christmasController.createOrder(orderRequest);

            DiscountResponse discountResponse = christmasController.discountEvent(order);
            FreeGiftResponse freeGiftResponse = christmasController.giftEvent(order);

            Assertions.assertThat(discountResponse.isEmpty()).isTrue();
            assertFreeGiftResponse(freeGiftResponse);
        }

        @DisplayName("증정 메뉴와 할인 내역 모두 없다면, 두 DTO 모두 비어있다.")
        @ParameterizedTest
        @CsvSource(value = {"티본스테이크-2:26", "바비큐립-2:27", "해산물파스타-3:28",
                "시저샐러드-14:29", "초코케이크-7:30"}, delimiter = ':')
        void nothing(String menuForm, int day) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = christmasController.createOrder(orderRequest);

            DiscountResponse discountResponse = christmasController.discountEvent(order);
            FreeGiftResponse freeGiftResponse = christmasController.giftEvent(order);

            Assertions.assertThat(discountResponse.isEmpty()).isTrue();
            Assertions.assertThat(freeGiftResponse.isEmpty()).isTrue();
        }

        @DisplayName("평일 할인 내역만 존재한다면, 증정 DTO는 비어있고 평일 할인 내역만 존재한다.")
        @ParameterizedTest
        @CsvSource(value = {"티본스테이크-1,초코케이크-1:-2023:26", "바비큐립-2,아이스크림-2:-4046:27",
                "해산물파스타-2,초코케이크-2,아이스크림-3:-10115:28"}, delimiter = ':')
        void onlyWeekDay(String menuForm, int discount, int day) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = christmasController.createOrder(orderRequest);

            DiscountResponse discountResponse = christmasController.discountEvent(order);
            FreeGiftResponse freeGiftResponse = christmasController.giftEvent(order);

            Assertions.assertThat(freeGiftResponse.isEmpty()).isTrue();
            Assertions.assertThat(discountResponse.isEmpty()).isFalse();
            Assertions.assertThat(discountResponse.getTotalDiscountAmount()).isEqualTo(discount);
            Assertions.assertThat(discountResponse.getEventNameAndDiscount())
                    .containsEntry("평일 할인", discount)
                    .size().isEqualTo(1);
        }

        @DisplayName("평일 할인 + 크리스마스 할인, 증정 X")
        @ParameterizedTest
        @CsvSource(value = {"티본스테이크-1,초코케이크-1:-2023:4", "바비큐립-2,아이스크림-2:-4046:12",
                "해산물파스타-2,초코케이크-2,아이스크림-3:-10115:20", "아이스크림-10,초코케이크-2:-24276:21"}, delimiter = ':')
        void weekDayAndChristmas(String menuForm, int discount, int day) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = christmasController.createOrder(orderRequest);
            int christmasDiscount = EventType.CHRISTMAS.calculateDiscountAmount(day, day);

            DiscountResponse discountResponse = christmasController.discountEvent(order);
            FreeGiftResponse freeGiftResponse = christmasController.giftEvent(order);

            Assertions.assertThat(freeGiftResponse.isEmpty()).isTrue();
            Assertions.assertThat(discountResponse.isEmpty()).isFalse();
            Assertions.assertThat(discountResponse.getEventNameAndDiscount())
                    .containsEntry("평일 할인", discount)
                    .containsEntry("크리스마스 디데이 할인", christmasDiscount)
                    .size().isEqualTo(2);
        }

        @DisplayName("평일 할인 + 크리스마스 할인 + 특별 할인, 증정 X")
        @ParameterizedTest
        @CsvSource(value = {"티본스테이크-1,초코케이크-1:-2023:3", "바비큐립-2,아이스크림-2:-4046:10",
                "해산물파스타-2,초코케이크-2,아이스크림-3:-10115:17", "아이스크림-10,초코케이크-2:-24276:24",
                "아이스크림-15,초코케이크-2:-34391:25"}, delimiter = ':')
        void weekDayAndChristmasAndSpecial(String menuForm, int discount, int day) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = christmasController.createOrder(orderRequest);
            int christmasDiscount = EventType.CHRISTMAS.calculateDiscountAmount(day, day);
            int specialDiscount = -1000;

            DiscountResponse discountResponse = christmasController.discountEvent(order);
            FreeGiftResponse freeGiftResponse = christmasController.giftEvent(order);

            Assertions.assertThat(freeGiftResponse.isEmpty()).isTrue();
            Assertions.assertThat(discountResponse.isEmpty()).isFalse();
            Assertions.assertThat(discountResponse.getEventNameAndDiscount())
                    .containsEntry("평일 할인", discount)
                    .containsEntry("크리스마스 디데이 할인", christmasDiscount)
                    .containsEntry("특별 할인", specialDiscount)
                    .size().isEqualTo(3);
        }

        @DisplayName("평일 할인 + 크리스마스 할인 + 특별 할인, 증정 O")
        @ParameterizedTest
        @CsvSource(value = {"티본스테이크-3,초코케이크-1:-2023:3", "바비큐립-3,아이스크림-2:-4046:10",
                "해산물파스타-5,초코케이크-2,아이스크림-3:-10115:17", "아이스크림-10,초코케이크-8:-36414:24",
                "아이스크림-15,초코케이크-2,티본스테이크-3:-34391:25"}, delimiter = ':')
        void weekDayAndChristmasAndSpecialAndGift(String menuForm, int discount, int day) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = christmasController.createOrder(orderRequest);
            int christmasDiscount = EventType.CHRISTMAS.calculateDiscountAmount(day, day);
            int specialDiscount = -1000;

            DiscountResponse discountResponse = christmasController.discountEvent(order);
            FreeGiftResponse freeGiftResponse = christmasController.giftEvent(order);

            Assertions.assertThat(freeGiftResponse.isEmpty()).isFalse();
            assertFreeGiftResponse(freeGiftResponse);

            Assertions.assertThat(discountResponse.isEmpty()).isFalse();
            Assertions.assertThat(discountResponse.getEventNameAndDiscount())
                    .containsEntry("평일 할인", discount)
                    .containsEntry("크리스마스 디데이 할인", christmasDiscount)
                    .containsEntry("특별 할인", specialDiscount)
                    .size().isEqualTo(3);
        }

        @DisplayName("주말이지만 주말 할인 못받은 경우")
        @ParameterizedTest
        @CsvSource(value = {"초코케이크-1:1", "아이스크림-2:2",
                "초코케이크-2,아이스크림-3:8", "아이스크림-10,초코케이크-8:23",
                "아이스크림-15,초코케이크-2:22"}, delimiter = ':')
        void weekEndNoDiscount(String menuForm, int day) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = christmasController.createOrder(orderRequest);

            DiscountResponse discountResponse = christmasController.discountEvent(order);
            Assertions.assertThat(discountResponse.getEventNameAndDiscount().containsKey("주말 할인")).isFalse();
        }

        @DisplayName("주말 할인 + 크리스마스 할인, 증정 상품 X")
        @ParameterizedTest
        @CsvSource(value = {"초코케이크-1,티본스테이크-1:-2023:1", "아이스크림-2,해산물파스타-2:-4046:2",
                "초코케이크-1,아이스크림-3,크리스마스파스타-3:-6069:8", "아이스크림-1,바비큐립-2:-4046:23",
                "아이스크림-1,제로콜라-1,티본스테이크-2:-4046:22"}, delimiter = ':')
        void weekEndAndChristmas(String menuForm, int weekendDiscount, int day) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = christmasController.createOrder(orderRequest);
            int christmasDiscount = EventType.CHRISTMAS.calculateDiscountAmount(day, day);

            FreeGiftResponse freeGiftResponse = christmasController.giftEvent(order);
            Assertions.assertThat(freeGiftResponse.isEmpty()).isTrue();

            DiscountResponse discountResponse = christmasController.discountEvent(order);
            Assertions.assertThat(discountResponse.getEventNameAndDiscount())
                    .containsEntry("주말 할인", weekendDiscount)
                    .containsEntry("크리스마스 디데이 할인", christmasDiscount)
                    .size().isEqualTo(2);
        }

        @DisplayName("주말 할인 + 크리스마스 할인 + 증정 상품")
        @ParameterizedTest
        @CsvSource(value = {"초코케이크-10,티본스테이크-1:-2023:1", "아이스크림-2,해산물파스타-10:-20230:2",
                "초코케이크-2,아이스크림-3,크리스마스파스타-3:-6069:8", "아이스크림-10,초코케이크-8,바비큐립-2:-4046:23",
                "아이스크림-15,초코케이크-2,티본스테이크-2:-4046:22"}, delimiter = ':')
        void weekEndAndChristmasAndFreeGift(String menuForm, int weekendDiscount, int day) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = christmasController.createOrder(orderRequest);
            int christmasDiscount = EventType.CHRISTMAS.calculateDiscountAmount(day, day);

            FreeGiftResponse freeGiftResponse = christmasController.giftEvent(order);
            Assertions.assertThat(freeGiftResponse.isEmpty()).isFalse();
            assertFreeGiftResponse(freeGiftResponse);

            DiscountResponse discountResponse = christmasController.discountEvent(order);
            Assertions.assertThat(discountResponse.getEventNameAndDiscount())
                    .containsEntry("주말 할인", weekendDiscount)
                    .containsEntry("크리스마스 디데이 할인", christmasDiscount)
                    .size().isEqualTo(2);
        }
    }

    private static void assertFreeGiftResponse(FreeGiftResponse freeGiftResponse) {
        Assertions.assertThat(freeGiftResponse.getBenefitPrice()).isEqualTo(-25000);
        Assertions.assertThat(freeGiftResponse.getName()).isEqualTo("샴페인");
        Assertions.assertThat(freeGiftResponse.getCount()).isEqualTo(1);
    }

}