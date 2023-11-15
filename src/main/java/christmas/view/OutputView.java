package christmas.view;

import java.text.DecimalFormat;

public class OutputView {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###원\n");
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printPreviewEventMessage(int date) {
        System.out.printf(PREVIEW_MESSAGE, date);
    }
}
