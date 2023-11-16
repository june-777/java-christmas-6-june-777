package christmas.view;

import christmas.dto.response.BadgeResponse;
import christmas.dto.response.DiscountResponse;
import christmas.dto.response.FreeGiftResponse;
import christmas.dto.response.OrderItemResponse;
import christmas.dto.response.OrderResponse;
import java.text.DecimalFormat;
import java.util.Map.Entry;

public class OutputView {

    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("###,###원\n");
    private static final String NOTHING = "없음";
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";

    private static final String ORDER_MENU_MESSAGE = "\n<주문 메뉴>";
    private static final String ORDER_MENU_RESULT_FORM = "%s %d개\n";

    private static final String TOTAL_ORDER_AMOUNT_MESSAGE = "\n<할인 전 총주문 금액>";

    private static final String FREE_GIFT_MESSAGE = "\n<증정 메뉴>";
    private static final String FREE_GIFT_RESULT_FORM = "%s %d개\n";
    private static final String FREE_GIFT = "증정 이벤트";
    private static final String BENEFIT_MESSAGE = "\n<혜택 내역>";
    private static final String BENEFIT_RESULT_FORM = "%s: %s";

    private static final String TOTAL_BENEFIT_PRICE_MESSAGE = "\n<총혜택 금액>";

    private static final String EXPECTED_PAYMENT_MESSAGE = "\n<할인 후 예상 결제 금액>";
    private static final String BADGE_MESSAGE = "\n<12월 이벤트 배지>";

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

    public void printFreeGiftResult(FreeGiftResponse freeGiftResponse) {
        System.out.println(FREE_GIFT_MESSAGE);
        if (freeGiftResponse.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }
        System.out.printf(FREE_GIFT_RESULT_FORM, freeGiftResponse.getName(), freeGiftResponse.getCount());
    }

    public void printBenefitResult(DiscountResponse discountResponse, FreeGiftResponse freeGiftResponse) {
        System.out.println(BENEFIT_MESSAGE);
        if (discountResponse.isEmpty() && freeGiftResponse.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }
        printDiscountBenefit(discountResponse);
        printFreeGiftBenefit(freeGiftResponse);
    }

    private void printDiscountBenefit(DiscountResponse discountResponse) {
        if (discountResponse.isEmpty()) {
            return;
        }
        for (Entry<String, Integer> eventNameAndDiscount : discountResponse.getEventNameAndDiscount().entrySet()) {
            System.out.printf(BENEFIT_RESULT_FORM, eventNameAndDiscount.getKey(),
                    PRICE_FORMAT.format(eventNameAndDiscount.getValue()));
        }
    }

    private void printFreeGiftBenefit(FreeGiftResponse freeGiftResponse) {
        if (freeGiftResponse.isEmpty()) {
            return;
        }
        System.out.printf(BENEFIT_RESULT_FORM, FREE_GIFT,
                PRICE_FORMAT.format(freeGiftResponse.getBenefitPrice()));
    }

    public void printTotalBenefitPrice(DiscountResponse discountResponse, FreeGiftResponse freeGiftResponse) {
        System.out.println(TOTAL_BENEFIT_PRICE_MESSAGE);
        int totalBenefitPrice = discountResponse.getTotalDiscountAmount() + freeGiftResponse.getBenefitPrice();
        System.out.printf(PRICE_FORMAT.format(totalBenefitPrice));
    }

    public void printExpectedPayment(OrderResponse orderResponse, DiscountResponse discountResponse) {
        System.out.println(EXPECTED_PAYMENT_MESSAGE);
        int expectedPayment = orderResponse.getTotalPrice() + discountResponse.getTotalDiscountAmount();
        System.out.printf(PRICE_FORMAT.format(expectedPayment));
    }

    public void printBadgeResult(BadgeResponse badgeResponse) {
        System.out.println(BADGE_MESSAGE);
        if (badgeResponse.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }
        System.out.print(badgeResponse.getBadgeName());
    }
}
