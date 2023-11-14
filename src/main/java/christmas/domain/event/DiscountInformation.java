package christmas.domain.event;

import christmas.domain.EventType;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DiscountInformation {
    private final EnumMap<EventType, Integer> eachEventDiscountAmounts;

    public DiscountInformation(EnumMap<EventType, Integer> eachEventDiscountAmounts) {
        this.eachEventDiscountAmounts = eachEventDiscountAmounts;
    }

    public int getTotalDiscountAmount() {
        int totalDiscountAmount = 0;
        for (Integer discountAmount : eachEventDiscountAmounts.values()) {
            totalDiscountAmount += discountAmount;
        }
        return totalDiscountAmount;
    }

    public int getDiscountAmount(EventType eventType) {
        if (eachEventDiscountAmounts.containsKey(eventType)) {
            return eachEventDiscountAmounts.get(eventType);
        }
        return 0;
    }

    public boolean isExistSpecificEventDiscount(EventType eventType) {
        return getDiscountAmount(eventType) != 0;
    }

    public Map<String, Integer> getEachEventDiscountAmounts() {
        Map<String, Integer> result = new HashMap<>();
        for (Entry<EventType, Integer> eachEventDiscountAmount : eachEventDiscountAmounts.entrySet()) {
            result.put(eachEventDiscountAmount.getKey().getEventName(), eachEventDiscountAmount.getValue());
        }
        return Collections.unmodifiableMap(result);
    }
}
