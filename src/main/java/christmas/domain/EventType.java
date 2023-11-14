package christmas.domain;

import christmas.service.event.EventDayCalculator;
import java.util.List;

public enum EventType {
    CHRISTMAS(EventDayCalculator.christmasDay()),
    WEEKDAY(EventDayCalculator.weekDay()),
    WEEKEND(EventDayCalculator.weekEnd()),
    SPECIAL(EventDayCalculator.specialDay());

    private final List<Integer> possibleDays;

    EventType(List<Integer> possibleDays) {
        this.possibleDays = possibleDays;
    }

    public List<Integer> getPossibleDays() {
        return possibleDays;
    }
}
