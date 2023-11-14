package christmas.domain;

import christmas.service.event.EventDayCalculator;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public enum EventType {
    CHRISTMAS(EventDayCalculator.christmasDay(), orderDay -> {
        final int basicDiscountAmount = 1000;
        final int discountAmountUnit = 100;
        return (basicDiscountAmount + discountAmountUnit * (orderDay - 1)) * -1;
    }),
    WEEKDAY(EventDayCalculator.weekDay(), dessertCount -> {
        final int discountAmountUnit = 2023;
        return (discountAmountUnit * dessertCount) * -1;
    }),
    WEEKEND(EventDayCalculator.weekEnd(), mainCourseCount -> {
        final int discountAmountUnit = 2023;
        return (discountAmountUnit * mainCourseCount) * -1;
    }),
    SPECIAL(EventDayCalculator.specialDay(), anything -> {
        return -1000;
    });

    private final List<Integer> possibleDays;
    private final Function<Integer, Integer> calculator;

    EventType(List<Integer> possibleDays, Function<Integer, Integer> calculator) {
        this.possibleDays = possibleDays;
        this.calculator = calculator;
    }

    public static List<EventType> getApplicableEventTypes(int targetDay) {
        List<EventType> applicableEventTypes = new ArrayList<>();
        for (EventType eventType : values()) {
            if (eventType.possibleDays.contains(targetDay)) {
                applicableEventTypes.add(eventType);
            }
        }
        return applicableEventTypes;
    }

    public int calculateDiscountAmount(int day, int calculateArgument) {
        if (this.possibleDays.contains(day)) {
            return calculator.apply(calculateArgument);
        }
        return 0;
    }

    public List<Integer> getPossibleDays() {
        return possibleDays;
    }
}
