package christmas.domain.exception;

import christmas.utils.message.OrderExceptionMessage;

public class ExceedTotalMenuCountException extends IllegalArgumentException {
    public ExceedTotalMenuCountException() {
        super(OrderExceptionMessage.MESSAGE.getError());
    }
}
