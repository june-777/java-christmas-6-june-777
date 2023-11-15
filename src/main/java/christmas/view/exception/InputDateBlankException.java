package christmas.view.exception;

import christmas.utils.message.DateExceptionMessage;

public class InputDateBlankException extends IllegalArgumentException {

    public InputDateBlankException() {
        super(DateExceptionMessage.MESSAGE.getError());
    }
}
