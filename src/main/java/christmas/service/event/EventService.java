package christmas.service.event;

import christmas.domain.event.DiscountInformation;
import christmas.domain.event.FreeGift;
import christmas.domain.order.Order;
import christmas.service.event.discount.DiscountEventService;
import christmas.service.event.gift.FreeGiftEventService;
import java.util.Optional;

public class EventService {

    private static final int APPLICABLE_MIN_TOTAL_PRICE = 10000;
    private final DiscountEventService discountEventService;
    private final FreeGiftEventService freeGiftEventService;

    public EventService(DiscountEventService discountEventService, FreeGiftEventService freeGiftEventService) {
        this.discountEventService = discountEventService;
        this.freeGiftEventService = freeGiftEventService;
    }

    public Optional<DiscountInformation> applyDiscountEvent(final Order order) {
        if (cantApplyEvent(order)) {
            return Optional.empty();
        }
        return Optional.of(discountEventService.calculateDiscountEvent(order));
    }

    public Optional<FreeGift> applyFreeGiftEvent(final Order order) {
        if (cantApplyEvent(order)) {
            return Optional.empty();
        }
        return freeGiftEventService.calculateGiftEvent(order.getTotalOrderPrice());
    }

    private boolean cantApplyEvent(final Order order) {
        return order.getTotalOrderPrice() < APPLICABLE_MIN_TOTAL_PRICE;
    }
}
