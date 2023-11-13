package christmas.domain.exception;

import christmas.utils.message.ExceptionMessage;

public class OutOfRangeOrderItemCountException extends IllegalArgumentException {
    public OutOfRangeOrderItemCountException() {
        super(ExceptionMessage.MESSAGE.getError());
    }
}
