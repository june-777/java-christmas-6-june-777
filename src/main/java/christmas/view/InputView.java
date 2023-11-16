package christmas.view;

import christmas.view.validator.DateInputValidator;
import christmas.view.validator.MenuFormInputValidator;

public class InputView {
    private static final String DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private final Reader reader;

    public InputView(Reader reader) {
        this.reader = reader;
    }

    public int readDate() {
        System.out.println(DATE_MESSAGE);
        String dateInput = reader.readLine().trim();
        DateInputValidator.validate(dateInput);
        return Integer.parseInt(dateInput);
    }

    public String readMenuForm() {
        System.out.println(MENU_MESSAGE);
        String menuForm = reader.readLine().trim();
        MenuFormInputValidator.validate(menuForm);
        return menuForm;
    }
}
