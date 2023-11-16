package christmas.utils.mapper;

import christmas.domain.EventType;
import christmas.domain.event.DiscountInformation;
import christmas.dto.response.DiscountResponse;
import java.util.HashMap;
import java.util.Map;

public class DiscountResponseMapper {
    public static DiscountResponse of(DiscountInformation discountInformation) {
        Map<String, Integer> result = new HashMap<>();
        for (EventType eventType : EventType.values()) {
            if (discountInformation.isExistSpecificEventDiscount(eventType)) {
                result.put(eventType.getEventName(), discountInformation.getDiscountAmount(eventType));
            }
        }
        return new DiscountResponse(result);
    }

    public static DiscountResponse of() {
        Map<String, Integer> result = new HashMap<>();
        return new DiscountResponse(result);
    }
}
