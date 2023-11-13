package christmas.domain.exception;

import christmas.utils.message.ExceptionMessage;

public class ExceedTotalMenuCountException extends IllegalArgumentException {
    public ExceedTotalMenuCountException() {
        super(ExceptionMessage.MESSAGE.getError());
    }
}
