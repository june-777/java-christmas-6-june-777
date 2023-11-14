package christmas.service;

import christmas.domain.order.Order;
import christmas.dto.EventResultResponse;
import christmas.dto.OrderRequest;
import christmas.dto.OrderRequestMapper;
import christmas.service.event.EventService;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventServiceTest {

    OrderService orderService = new OrderService();
    EventService eventService = new EventService();

    @Nested
    @DisplayName("이벤트 적용이 가능한지 확인하는 기능 테스트")
    class CanApplyEventTest {
        @ParameterizedTest
        @ValueSource(strings = {"양송이수프-1", "타파스-1", "시저샐러드-1",
                "양송이수프-1,제로콜라-1", "타파스-1,제로콜라-1"})
        @DisplayName("총 주문 금액이 10,000원 미만이면 이벤트 적용이 안된다.")
        void cantApplyEvent(String menuInput) {
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuInput, 3);
            Order order = orderService.createOrder(orderRequest);

            Optional<EventResultResponse> result = eventService.applyEvent(order);
            Assertions.assertThat(result).isEmpty();
        }

        // TODO: EventService 완성되면 이벤트 적용 여부 테스트
        void canApplyEvent(String menuInput) {

        }
    }

    // TODO:
    @Nested
    @DisplayName("크리스마스 디데이 할인 적용 테스트")
    class ChristmasEventTest {

    }

    // TODO:
    @Nested
    @DisplayName("평일 할인 적용 테스트")
    class WeekDayEventTest {

    }

    // TODO:
    @Nested
    @DisplayName("평일 할인 적용 테스트")
    class WeekEndTest {

    }

    // TODO:
    @Nested
    @DisplayName("스페셜 할인 적용 테스트")
    class SpecialTest {

    }
}