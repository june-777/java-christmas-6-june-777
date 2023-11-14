package christmas.domain.exception;

import christmas.utils.message.OrderExceptionMessage;

public class OnlyBeverageMenuException extends IllegalArgumentException {
    public OnlyBeverageMenuException() {
        super(OrderExceptionMessage.MESSAGE.getError());
    }
}
