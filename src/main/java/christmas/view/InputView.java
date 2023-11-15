package christmas.view;

import christmas.view.validator.DateInputValidator;

public class InputView {
    private static final String DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
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
}
