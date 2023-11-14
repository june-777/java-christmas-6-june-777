package christmas.domain;

import christmas.service.event.EventDayCalculator;
import java.util.ArrayList;
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

    public static List<EventType> getApplicableEventTypes(int targetDay) {
        List<EventType> applicableEventTypes = new ArrayList<>();
        for (EventType eventType : values()) {
            if (eventType.possibleDays.contains(targetDay)) {
                applicableEventTypes.add(eventType);
            }
        }
        return applicableEventTypes;
    }

    public List<Integer> getPossibleDays() {
        return possibleDays;
    }
}
