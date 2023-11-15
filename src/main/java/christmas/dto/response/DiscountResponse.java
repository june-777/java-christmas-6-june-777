package christmas.dto.response;

import java.util.Map;

public class DiscountResponse {
    private final Map<String, Integer> eventNameAndDiscount;

    public DiscountResponse(Map<String, Integer> eventNameAndDiscount) {
        this.eventNameAndDiscount = eventNameAndDiscount;
    }

    public Map<String, Integer> getEventNameAndDiscount() {
        return eventNameAndDiscount;
    }

    public boolean isEmpty() {
        return eventNameAndDiscount == null || eventNameAndDiscount.isEmpty();
    }

    public int getTotalDiscountAmount() {
        int sum = 0;
        for (int discountAmount : eventNameAndDiscount.values()) {
            sum += discountAmount;
        }
        return sum;
    }

    @Override
    public String toString() {
        return "DiscountResponse{" +
                "eventNameAndDiscount=" + eventNameAndDiscount +
                '}';
    }
}
