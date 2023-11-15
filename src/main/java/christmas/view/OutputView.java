package christmas.view;

import christmas.dto.response.OrderItemResponse;
import christmas.dto.response.OrderResponse;
import java.text.DecimalFormat;

public class OutputView {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###원\n");
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    private static final String ORDER_MENU_MESSAGE = "\n<주문 메뉴>";
    private static final String ORDER_MENU_RESULT_FORM = "%s %d개\n";

    private static final String TOTAL_ORDER_AMOUNT_MESSAGE = "\n<할인 전 총주문 금액>";

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printPreviewEventMessage(int date) {
        System.out.printf(PREVIEW_MESSAGE, date);
    }

    public void printOrderMenu(OrderResponse orderResponse) {
        System.out.println(ORDER_MENU_MESSAGE);
        for (OrderItemResponse orderItemResponse : orderResponse.getOrderItemResponses()) {
            System.out.printf(ORDER_MENU_RESULT_FORM, orderItemResponse.getMenuName(), orderItemResponse.getCount());
        }
    }

    public void printTotalOrderAmount(OrderResponse orderResponse) {
        System.out.println(TOTAL_ORDER_AMOUNT_MESSAGE);
        System.out.printf(PRICE_FORMAT.format(orderResponse.getTotalPrice()));
    }
}
