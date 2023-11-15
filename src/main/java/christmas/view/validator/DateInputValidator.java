package christmas.view.validator;

import christmas.view.exception.InputDateBlankException;
import christmas.view.exception.InputDateNotNumericException;
import java.util.regex.Pattern;

public class DateInputValidator {

    private static final Pattern NUMERIC_REGEX = Pattern.compile("[1-9]\\d*");

    public static void validate(String target) {
        validateBlank(target);
        validateNumeric(target);
    }

    private static void validateBlank(String target) {
        if (target == null || target.isBlank()) {
            throw new InputDateBlankException();
        }
    }

    private static void validateNumeric(String target) {
        if (!NUMERIC_REGEX.matcher(target).matches()) {
            throw new InputDateNotNumericException();
        }
    }

}
