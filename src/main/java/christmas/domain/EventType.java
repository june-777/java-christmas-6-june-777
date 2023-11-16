package christmas.domain;

import christmas.service.event.EventDayCalculator;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public enum EventType {
    CHRISTMAS("크리스마스 디데이 할인", EventDayCalculator.christmasDay(), orderDay -> {
        final int basicDiscountAmount = 1000;
        final int discountAmountUnit = 100;
        return (basicDiscountAmount + discountAmountUnit * (orderDay - 1)) * -1;
    }),
    WEEKDAY("평일 할인", EventDayCalculator.weekDay(), dessertCount -> {
        final int discountAmountUnit = 2023;
        return (discountAmountUnit * dessertCount) * -1;
    }),
    WEEKEND("주말 할인", EventDayCalculator.weekEnd(), mainCourseCount -> {
        final int discountAmountUnit = 2023;
        return (discountAmountUnit * mainCourseCount) * -1;
    }),
    SPECIAL("특별 할인", EventDayCalculator.specialDay(), anything -> {
        return -1000;
    });

    private final String eventName;
    private final List<Integer> possibleDays;
    private final Function<Integer, Integer> calculator;

    EventType(String eventName, List<Integer> possibleDays, Function<Integer, Integer> calculator) {
        this.eventName = eventName;
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

    public String getEventName() {
        return eventName;
    }
}
