package christmas.service.event;

import java.util.ArrayList;
import java.util.List;

public class EventDayCalculator {

    private static final int CHRISTMAS_EVENT_START = 1;
    private static final int CHRISTMAS_EVENT_END = 25;

    public static List<Integer> christmasDay() {
        List<Integer> days = new ArrayList<>();
        for (int day = CHRISTMAS_EVENT_START; day <= CHRISTMAS_EVENT_END; day++) {
            days.add(day);
        }
        return days;
    }
}
