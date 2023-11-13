package christmas.domain.exception;

import christmas.utils.message.ExceptionMessage;

public class MenuNotFoundException extends IllegalArgumentException {
    public MenuNotFoundException() {
        super(ExceptionMessage.MESSAGE.getError());
    }
}