package christmas.domain.exception;

import christmas.utils.message.ExceptionMessage;

public class DuplicateMenuException extends IllegalArgumentException {
    public DuplicateMenuException() {
        super(ExceptionMessage.MESSAGE.getError());
    }
}
