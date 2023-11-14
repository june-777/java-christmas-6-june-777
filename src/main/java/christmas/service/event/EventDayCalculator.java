package christmas.service.event;

import java.util.ArrayList;
import java.util.List;

public class EventDayCalculator {

    private static final int CHRISTMAS_EVENT_START = 1;
    private static final int CHRISTMAS_EVENT_END = 25;

    private static final int FIRST_WEEK_FRIDAY = 1;
    private static final int FIRST_WEEK_SATURDAY = 2;
    private static final int DECEMBER_LAST_DAY = 31;
    private static final int DAY_OF_WEEK = 7;


    public static List<Integer> christmasDay() {
        List<Integer> days = new ArrayList<>();
        for (int day = CHRISTMAS_EVENT_START; day <= CHRISTMAS_EVENT_END; day++) {
            days.add(day);
        }
        return days;
    }

    public static List<Integer> weekEnd() {
        List<Integer> days = new ArrayList<>();
        for (int friday = FIRST_WEEK_FRIDAY, saturday = FIRST_WEEK_SATURDAY; saturday <= DECEMBER_LAST_DAY;
             friday += DAY_OF_WEEK, saturday += DAY_OF_WEEK) {
            days.add(friday);
            days.add(saturday);
        }
        return days;
    }
}
