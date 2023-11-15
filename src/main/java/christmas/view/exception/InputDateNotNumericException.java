package christmas.view.exception;

import christmas.utils.message.DateExceptionMessage;

public class InputDateNotNumericException extends IllegalArgumentException {

    public InputDateNotNumericException() {
        super(DateExceptionMessage.MESSAGE.getError());
    }
}
