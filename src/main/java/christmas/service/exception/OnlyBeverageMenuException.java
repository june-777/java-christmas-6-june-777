package christmas.service.exception;

import christmas.utils.message.ExceptionMessage;

public class OnlyBeverageMenuException extends IllegalArgumentException {
    public OnlyBeverageMenuException() {
        super(ExceptionMessage.MESSAGE.getError());
    }
}
