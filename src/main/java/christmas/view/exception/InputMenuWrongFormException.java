package christmas.view.exception;

import christmas.utils.message.OrderExceptionMessage;

public class InputMenuWrongFormException extends IllegalArgumentException {
    public InputMenuWrongFormException() {
        super(OrderExceptionMessage.MESSAGE.getError());
    }
}
