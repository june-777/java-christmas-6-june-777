package christmas.domain.exception;

import christmas.utils.message.DateExceptionMessage;

public class OutOfRangeDayException extends IllegalArgumentException {
    public OutOfRangeDayException() {
        super(DateExceptionMessage.MESSAGE.getError());
    }
}
