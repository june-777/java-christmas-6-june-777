package christmas.domain;

import java.util.EnumMap;

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
}
