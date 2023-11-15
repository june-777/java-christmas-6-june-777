package christmas.view.validator;

import christmas.view.exception.InputDateBlankException;

public class DateInputValidator {

    public static void validate(String target) {
        validateBlank(target);
    }

    private static void validateBlank(String target) {
        if (target == null || target.isBlank()) {
            throw new InputDateBlankException();
        }
    }
}
