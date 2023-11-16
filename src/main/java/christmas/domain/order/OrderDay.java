package christmas.domain.order;

import christmas.domain.exception.OutOfRangeDayException;

public class OrderDay {
    private static final int MIN_DAY = 1;
    private static final int MAX_DAY = 31;
    private final int day;

    public OrderDay(int day) {
        validate(day);
        this.day = day;
    }

    private void validate(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new OutOfRangeDayException();
        }
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return String.valueOf(day);
    }
}
