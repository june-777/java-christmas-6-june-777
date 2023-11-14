package christmas.domain;

import java.util.EnumMap;

public class DiscountInformation {
    private final EnumMap<EventType, Integer> eachEventDiscountAmounts;

    public DiscountInformation(EnumMap<EventType, Integer> eachEventDiscountAmounts) {
        this.eachEventDiscountAmounts = eachEventDiscountAmounts;
    }
}
