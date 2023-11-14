package christmas.service.event.gift;

import christmas.domain.Menu;
import christmas.domain.event.FreeGift;
import java.util.Optional;

public class FreeGiftEventService {

    private static final int APPLICABLE_MIN_AMOUNT = 120000;
    private static final int freeGiftCount = 1;

    public Optional<FreeGift> calculateGiftEvent(int totalOrderPrice) {
        if (totalOrderPrice >= APPLICABLE_MIN_AMOUNT) {
            return Optional.of(new FreeGift(Menu.CHAMPAGNE, freeGiftCount));
        }
        return Optional.empty();
    }
}
