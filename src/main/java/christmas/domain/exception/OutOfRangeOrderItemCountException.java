package christmas.domain.exception;

import christmas.utils.message.OrderExceptionMessage;

public class OutOfRangeOrderItemCountException extends IllegalArgumentException {
    public OutOfRangeOrderItemCountException() {
        super(OrderExceptionMessage.MESSAGE.getError());
    }
}
