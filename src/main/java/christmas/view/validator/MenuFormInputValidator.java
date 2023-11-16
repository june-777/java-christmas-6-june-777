package christmas.view.validator;

import christmas.view.exception.InputMenuWrongFormException;
import java.util.Arrays;
import java.util.List;

public class MenuFormInputValidator {

    private static final char COMMA = ',';
    private static final char HYPHEN = '-';
    private static final String COMMA_REGEX = ",";
    private static final String HYPHEN_REGEX = "-";
    private static final int MENU_NAME_INDEX = 0;
    private static final int MENU_COUNT_INDEX = 1;

    public static void validate(String target) {
        validateMenuForm(target);

        List<String> menuAndCount = Arrays.asList(target.split(COMMA_REGEX));
        validateMenuAndCountForm(menuAndCount);
        validateMenuName(menuAndCount);
        validateMenuCount(menuAndCount);
    }

    private static void validateMenuForm(String target) {
        validateBlank(target);
        validateFirstCharacterIsComma(target);
        validateLastCharacterIsComma(target);
        validateContinuousComma(target);
    }

    private static void validateBlank(String target) {
        if (target == null || target.isBlank()) {
            throw new InputMenuWrongFormException();
        }
    }

    private static void validateFirstCharacterIsComma(String target) {
        if (target.charAt(0) == COMMA) {
            throw new InputMenuWrongFormException();
        }
    }

    private static void validateLastCharacterIsComma(String target) {
        if (target.charAt(target.length() - 1) == COMMA) {
            throw new InputMenuWrongFormException();
        }
    }

    private static void validateContinuousComma(String target) {
        boolean blankExist = Arrays.stream(target.split(COMMA_REGEX))
                .anyMatch(String::isBlank);

        if (blankExist) {
            throw new InputMenuWrongFormException();
        }
    }

    private static void validateMenuName(List<String> menuAndCount) {
        validateMenuNameIsBlank(menuAndCount);
    }

    private static void validateMenuNameIsBlank(List<String> menuAndCount) {
        boolean isBlank = menuAndCount.stream()
                .map(element -> element.split(HYPHEN_REGEX))
                .anyMatch(splited -> splited[MENU_NAME_INDEX] == null || splited[MENU_NAME_INDEX].isBlank());

        if (isBlank) {
            throw new InputMenuWrongFormException();
        }
    }

    private static void validateMenuCount(List<String> menuAndCount) {
        validateCountIsBlank(menuAndCount);
        validateCountIsNumeric(menuAndCount);
    }

    private static void validateCountIsBlank(List<String> menuAndCount) {
        boolean isBlank = menuAndCount.stream()
                .map(element -> element.split(HYPHEN_REGEX))
                .anyMatch(splited -> splited[MENU_COUNT_INDEX] == null || splited[MENU_COUNT_INDEX].isBlank());

        if (isBlank) {
            throw new InputMenuWrongFormException();
        }
    }

    private static void validateCountIsNumeric(List<String> menuAndCount) {
        boolean notNumeric = menuAndCount.stream()
                .map(element -> element.split(HYPHEN_REGEX))
                .anyMatch(splited -> !isNumeric(splited[MENU_COUNT_INDEX]));

        if (notNumeric) {
            throw new InputMenuWrongFormException();
        }
    }

    private static boolean isNumeric(String target) {
        try {
            Integer.parseInt(target);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void validateMenuAndCountForm(List<String> menuAndCountForm) {
        validateEachElementIsBlank(menuAndCountForm);
        validateContainsHyphen(menuAndCountForm);
        validateOnlyOneHyphen(menuAndCountForm);
        validateFirstCharacterIsHyphen(menuAndCountForm);
        validateLastCharacterIsHyphen(menuAndCountForm);
    }

    private static void validateEachElementIsBlank(List<String> menuAndCountForm) {
        boolean blankExists = menuAndCountForm.stream()
                .anyMatch(element -> element.isBlank());

        if (blankExists) {
            throw new InputMenuWrongFormException();
        }
    }

    private static void validateContainsHyphen(List<String> menuAndCountForm) {
        boolean notContainsHyphen = menuAndCountForm.stream()
                .anyMatch(element -> !element.contains(HYPHEN_REGEX));

        if (notContainsHyphen) {
            throw new InputMenuWrongFormException();
        }
    }

    private static void validateOnlyOneHyphen(List<String> menuAndCountForm) {
        boolean allElementHasOnlyOneHyphen = menuAndCountForm.stream()
                .allMatch(menu -> menu.chars().filter(character -> character == HYPHEN).count() == 1);

        if (!allElementHasOnlyOneHyphen) {
            throw new InputMenuWrongFormException();
        }
    }

    private static void validateFirstCharacterIsHyphen(List<String> menuAndCountForm) {
        boolean exists = menuAndCountForm.stream()
                .anyMatch(element -> element.charAt(0) == HYPHEN);

        if (exists) {
            throw new InputMenuWrongFormException();
        }
    }

    private static void validateLastCharacterIsHyphen(List<String> menuAndCountForm) {
        boolean exists = menuAndCountForm.stream()
                .anyMatch(element -> element.charAt(element.length() - 1) == HYPHEN);

        if (exists) {
            throw new InputMenuWrongFormException();
        }
    }
}
