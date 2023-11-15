package christmas.view.exception;

import christmas.utils.message.DateExceptionMessage;

public class InputDateNotIntegerException extends IllegalArgumentException {

    public InputDateNotIntegerException() {
        super(DateExceptionMessage.MESSAGE.getError());
    }
}
