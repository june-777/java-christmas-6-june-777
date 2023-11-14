package christmas.domain;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.dto.OrderRequest;
import christmas.dto.OrderRequestMapper;
import christmas.service.OrderService;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class EventTypeTest {

    private static final String ORDER_ITEMS_GENERATOR_PATH = "christmas.helper.OrderItemsGenerator";
    OrderService orderService = new OrderService();

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

    @Nested
    @DisplayName("평일 할인 이벤트를 적용하려는데")
    class WeekDayDiscountTest {

        @DisplayName("적용 가능 날짜가 아니라면 0원이 할인된다.")
        @ParameterizedTest
        @CsvSource(value = {"1:초코케이크-1", "2:초코케이크-2",
                "8:아이스크림-4", "9:아이스크림-1",
                "15:아이스크림-4", "16:아이스크림-1",
                "22:아이스크림-8", "23:아이스크림-9",
                "29:아이스크림-4,타파스-2", "30:아이스크림-1,바비큐립-2"}, delimiter = ':')
        void notWeekDay(int day, String menuForm) {
            // given
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = orderService.createOrder(orderRequest);
            int expectedDiscountAmount = 0;
            // when
            int discountAmount = EventType.WEEKDAY.calculateDiscountAmount(day, day);
            // then
            Assertions.assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
        }

        @DisplayName("디저트 개수가 없다면 0원이 할인된다.")
        @ParameterizedTest
        @CsvSource(value = {"3:양송이수프-1", "5:타파스-2",
                "11:시저샐러드-4", "14:티본스테이크-1",
                "17:해산물파스타-4", "21:크리스마스파스타-1",
                "25:바비큐립-1,제로콜라-8", "26:타파스-1,바비큐립-9",
                "31:양송이수프-1,티본스테이크-1,레드와인-1,타파스-2"}, delimiter = ':')
        void notExistDessertMenu(int day, String menuForm) {
            // given
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = orderService.createOrder(orderRequest);
            int dessertMenuCount = order.getSpecificMenuOrderCount(MenuType.DESSERT);
            int expectedDiscountAmount = 0;
            // when
            int discountAmount = EventType.WEEKDAY.calculateDiscountAmount(day, dessertMenuCount);
            // then
            Assertions.assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
        }

        @DisplayName("여러 메뉴 중 디저트 개수만큼 할인된다.")
        @ParameterizedTest
        @CsvSource(value = {"3:양송이수프-1,초코케이크-1:-2023", "5:타파스-2,아이스크림-2:-4046",
                "11:시저샐러드-4,초코케이크-1,아이스크림-2:-6069", "14:티본스테이크-1,초코케이크-4:-8092",
                "17:초코케이크-11:-22253", "21:아이스크림-17:-34391",
                "25:초코케이크-17,아이스크림-2:-38437", "26:아이스크림-9,초코케이크-11:-40460",
                "31:초코케이크-5,아이스크림-4:-18207"}, delimiter = ':')
        void discountDessertCount(int day, String menuForm, int expectedDiscountAmount) {
            // given
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = orderService.createOrder(orderRequest);
            int dessertMenuCount = order.getSpecificMenuOrderCount(MenuType.DESSERT);
            // when
            int discountAmount = EventType.WEEKDAY.calculateDiscountAmount(day, dessertMenuCount);
            // then
            Assertions.assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
        }

        @DisplayName("디저트 개수만큼 할인된다.")
        @ParameterizedTest
        @CsvSource(value = {"1:-2023", "2:-4046", "3:-6069", "4:-8092", "5:-10115",
                "6:-12138", "7:-14161", "8:-16184", "9:-18207", "10:-20230",
                "11:-22253", "12:-24276", "13:-26299", "14:-28322", "15:-30345",
                "16:-32368", "17:-34391", "18:-36414", "19:-38437", "20:-40460"}, delimiter = ':')
        void weekDayDiscountAmountManyCase(int dessertCount, int expectedDiscountAmount) {
            // given
            List<Integer> weekDays = EventType.WEEKDAY.getPossibleDays();
            for (Integer weekDay : weekDays) {
                EventType eventType = EventType.WEEKDAY;
                // when
                int resultDiscountAmount = eventType.calculateDiscountAmount(weekDay, dessertCount);
                // then
                Assertions.assertThat(resultDiscountAmount).isEqualTo(expectedDiscountAmount);
            }
        }

        @DisplayName("여러 메뉴 중 디저트가 2개면, -4046원 할인된다.")
        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createSuccessOrder")
        void weekDayDiscountAmount(List<OrderItem> orderItems) {
            // given
            List<Integer> weekDays = EventType.WEEKDAY.getPossibleDays();
            int expectedDiscountAmount = -4046;
            for (Integer weekDay : weekDays) {
                Order order = new Order(orderItems, weekDay);
                EventType eventType = EventType.WEEKDAY;
                // when
                int resultDiscountAmount = eventType.calculateDiscountAmount(weekDay,
                        order.getSpecificMenuOrderCount(MenuType.DESSERT));
                // then
                Assertions.assertThat(resultDiscountAmount).isEqualTo(expectedDiscountAmount);
            }
        }

        @DisplayName("여러 메뉴 중 디저트가 16개면, -32368원 할인된다.")
        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createManyDessertOrder")
        void weekDayDiscountAmountManyDessert(List<OrderItem> orderItems) {
            // given
            List<Integer> weekDays = EventType.WEEKDAY.getPossibleDays();
            int expectedDiscountAmount = -32368;
            for (Integer weekDay : weekDays) {
                Order order = new Order(orderItems, weekDay);
                EventType eventType = EventType.WEEKDAY;
                // when
                int resultDiscountAmount = eventType.calculateDiscountAmount(weekDay,
                        order.getSpecificMenuOrderCount(MenuType.DESSERT));
                // then
                Assertions.assertThat(resultDiscountAmount).isEqualTo(expectedDiscountAmount);
            }
        }
    }

    @Nested
    @DisplayName("주말 할인 이벤트를 적용하려는데")
    class WeekendDiscountTest {

        @DisplayName("적용 가능 날짜가 아니라면 0원이 할인된다.")
        @ParameterizedTest
        @CsvSource(value = {"3:티본스테이크-1", "4:바비큐립-2",
                "5:해산물파스타-4", "6:크리스마스파스타-1",
                "7:티본스테이크-4,해산물파스타-1", "10:바비큐립-1,크리스마스파스타-4",
                "11:티본스테이크-8,해산물파스타-1,제로콜라-1", "12:크리스마스파스타-9,양송이수프-1",
                "21:티본스테이크-4,타파스-2", "28:레드와인-1,바비큐립-2",
                "31:티본스테이크-1"}, delimiter = ':')
        void notWeekend(int day, String menuForm) {
            // given
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = orderService.createOrder(orderRequest);
            int expectedDiscountAmount = 0;
            // when
            int discountAmount = EventType.WEEKEND.calculateDiscountAmount(day, day);
            // then
            Assertions.assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
        }

        @DisplayName("디저트 개수가 없다면 0원이 할인된다.")
        @ParameterizedTest
        @CsvSource(value = {"1:양송이수프-1", "2:타파스-2",
                "8:시저샐러드-4", "9:초코케이크-1",
                "15:아이스크림-4", "16:양송이수프-1,초코케이크-1",
                "22:타파스-1,제로콜라-8", "23:타파스-1,제로콜라-9",
                "29:양송이수프-1,아이스크림-1,레드와인-1,타파스-2", "30:초코케이크-1,제로콜라-1"}, delimiter = ':')
        void notExistDessertMenu(int day, String menuForm) {
            // given
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, day);
            Order order = orderService.createOrder(orderRequest);
            int dessertMenuCount = order.getSpecificMenuOrderCount(MenuType.MAIN_COURSE);
            int expectedDiscountAmount = 0;
            // when
            int discountAmount = EventType.WEEKEND.calculateDiscountAmount(day, dessertMenuCount);
            // then
            Assertions.assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
        }

        @DisplayName("여러 메뉴 중 메인 메뉴 개수가 4개라면 -8092원만큼 할인된다.")
        @ParameterizedTest
        @MethodSource(ORDER_ITEMS_GENERATOR_PATH + "#createSuccessOrder")
        void weekDayDiscountAmount(List<OrderItem> orderItems) {
            // given
            List<Integer> weekDays = EventType.WEEKEND.getPossibleDays();
            int expectedDiscountAmount = -8092;
            for (Integer weekDay : weekDays) {
                Order order = new Order(orderItems, weekDay);
                EventType eventType = EventType.WEEKEND;
                // when
                int resultDiscountAmount = eventType.calculateDiscountAmount(weekDay,
                        order.getSpecificMenuOrderCount(MenuType.MAIN_COURSE));
                // then
                Assertions.assertThat(resultDiscountAmount).isEqualTo(expectedDiscountAmount);
            }
        }

        @DisplayName("메인 메뉴 개수만큼 할인된다.")
        @ParameterizedTest
        @CsvSource(value = {"1:-2023", "2:-4046", "3:-6069", "4:-8092", "5:-10115",
                "6:-12138", "7:-14161", "8:-16184", "9:-18207", "10:-20230",
                "11:-22253", "12:-24276", "13:-26299", "14:-28322", "15:-30345",
                "16:-32368", "17:-34391", "18:-36414", "19:-38437", "20:-40460"}, delimiter = ':')
        void weekEndDiscountAmountManyCase(int mainCourseCount, int expectedDiscountAmount) {
            // given
            List<Integer> weekEnds = EventType.WEEKEND.getPossibleDays();
            for (Integer weekEnd : weekEnds) {
                EventType eventType = EventType.WEEKEND;
                // when
                int resultDiscountAmount = eventType.calculateDiscountAmount(weekEnd, mainCourseCount);
                // then
                Assertions.assertThat(resultDiscountAmount).isEqualTo(expectedDiscountAmount);
            }
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