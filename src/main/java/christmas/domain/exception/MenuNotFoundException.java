package christmas.domain.exception;

import christmas.utils.message.OrderExceptionMessage;

public class MenuNotFoundException extends IllegalArgumentException {
    public MenuNotFoundException() {
        super(OrderExceptionMessage.MESSAGE.getError());
    }
}