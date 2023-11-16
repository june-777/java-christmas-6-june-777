package christmas.domain.exception;

import christmas.utils.message.OrderExceptionMessage;

public class DuplicateMenuException extends IllegalArgumentException {
    public DuplicateMenuException() {
        super(OrderExceptionMessage.MESSAGE.getError());
    }
}
