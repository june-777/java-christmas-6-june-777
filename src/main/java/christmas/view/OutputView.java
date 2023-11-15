package christmas.view;

import java.text.DecimalFormat;

public class OutputView {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###원\n");
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }
}
