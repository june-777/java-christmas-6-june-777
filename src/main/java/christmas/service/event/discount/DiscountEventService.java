package christmas.service.event.discount;

import christmas.domain.DiscountInformation;
import christmas.domain.EventType;
import christmas.domain.order.Order;
import christmas.service.event.discount.executor.ChristmasDiscountExecutor;
import christmas.service.event.discount.executor.DiscountExecutor;
import christmas.service.event.discount.executor.SpecialDayDiscountExecutor;
import christmas.service.event.discount.executor.WeekDayDiscountExecutor;
import christmas.service.event.discount.executor.WeekEndEventExecutor;
import java.util.EnumMap;
import java.util.List;

public class DiscountEventService {

    private final EnumMap<EventType, DiscountExecutor> discountExecutor;

    public DiscountEventService() {
        EnumMap<EventType, DiscountExecutor> discountExecutor = new EnumMap<>(EventType.class);
        discountExecutor.put(EventType.CHRISTMAS, new ChristmasDiscountExecutor());
        discountExecutor.put(EventType.WEEKDAY, new WeekDayDiscountExecutor());
        discountExecutor.put(EventType.WEEKEND, new WeekEndEventExecutor());
        discountExecutor.put(EventType.SPECIAL, new SpecialDayDiscountExecutor());
        this.discountExecutor = discountExecutor;
    }

    public DiscountInformation applyDiscountEvent(Order order) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(order.getDay());
        EnumMap<EventType, Integer> discounts = updateEachEventDiscount(order, applicableEventTypes);
        return new DiscountInformation(discounts);
    }
    
}
