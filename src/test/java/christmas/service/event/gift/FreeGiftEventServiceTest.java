package christmas.service.event.gift;

import christmas.domain.Menu;
import christmas.domain.event.FreeGift;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FreeGiftEventServiceTest {

    FreeGiftEventService freeGiftEventService = new FreeGiftEventService();

    @ParameterizedTest
    @ValueSource(ints = {119999})
    @DisplayName("총주문 금액이 12만원 미만이면 증정 상품이 없다.")
    void notExistFreeGift(int totalOrderPrice) {
        Optional<FreeGift> freeGift = freeGiftEventService.calculateGiftEvent(totalOrderPrice);
        Assertions.assertThat(freeGift).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {120000, 131313, 123456})
    @DisplayName("총주문 금액이 12만원 이상이면 증정 상품이 있다.")
    void existFreeGift(int totalOrderPrice) {
        Optional<FreeGift> freeGift = freeGiftEventService.calculateGiftEvent(totalOrderPrice);
        Assertions.assertThat(freeGift).isPresent();
        Assertions.assertThat(freeGift.get().getGiftName()).isEqualTo(Menu.CHAMPAGNE.getName());
        Assertions.assertThat(freeGift.get().getCount()).isEqualTo(1);
        Assertions.assertThat(freeGift.get().calculateBenefitPrice()).isEqualTo(Menu.CHAMPAGNE.getPrice() * -1);
    }
}