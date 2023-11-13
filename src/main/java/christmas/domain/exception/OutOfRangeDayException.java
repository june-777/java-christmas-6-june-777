package christmas.domain.exception;

import christmas.utils.message.ExceptionMessage;

public class OutOfRangeDayException extends IllegalArgumentException {
    public OutOfRangeDayException() {
        super(ExceptionMessage.MESSAGE.getError());
    }
}
