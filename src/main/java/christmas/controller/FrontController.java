package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.order.OrderDay;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.BadgeResponse;
import christmas.dto.response.DiscountResponse;
import christmas.dto.response.FreeGiftResponse;
import christmas.dto.response.OrderResponse;
import christmas.utils.mapper.OrderRequestMapper;
import christmas.utils.mapper.OrderResponseMapper;
import christmas.view.InputView;
import christmas.view.OutputView;

public class FrontController {
    private final ChristmasController christmasController;
    private final InputView inputView;
    private final OutputView outputView;

    public FrontController(ChristmasController christmasController, InputView inputView, OutputView outputView) {
        this.christmasController = christmasController;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        startChristmasPromotion();
        OrderDay orderDay = dateSetting();
        Order order = orderSetting(orderDay.getDay());
        OrderResponse orderResponse = OrderResponseMapper.fromOrder(order);

        renderingPreview(orderDay, orderResponse);
        processChristmasPromotion(order, orderResponse);
    }

    private void startChristmasPromotion() {
        outputView.printStartMessage();
    }

    public OrderDay dateSetting() {
        return ExceptionHandler.handle(() -> {
            int date = inputView.readDate();
            return christmasController.createOrderDay(date);
        });
    }

    public Order orderSetting(int date) {
        return ExceptionHandler.handle(() -> {
            String menuForm = inputView.readMenuForm();
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, date);
            return christmasController.createOrder(orderRequest);
        });
    }

    private void renderingPreview(OrderDay orderDay, OrderResponse orderResponse) {
        outputView.printPreviewEventMessage(orderDay.getDay());
        outputView.printOrderMenu(orderResponse);
        outputView.printTotalOrderAmount(orderResponse);
    }

    private void processChristmasPromotion(Order order, OrderResponse orderResponse) {
        DiscountResponse discountResponse = christmasController.discountEvent(order);
        FreeGiftResponse freeGiftResponse = christmasController.giftEvent(order);
        renderingEventResult(orderResponse, freeGiftResponse, discountResponse);

        BadgeResponse badgeResponse = christmasController.badge(discountResponse.getTotalDiscountAmount(),
                freeGiftResponse.getBenefitPrice());
        renderingBadgeResult(badgeResponse);
    }

    private void renderingEventResult(OrderResponse orderResponse, FreeGiftResponse freeGiftResponse,
                                      DiscountResponse discountResponse) {
        outputView.printFreeGiftResult(freeGiftResponse);
        outputView.printBenefitResult(discountResponse, freeGiftResponse);
        outputView.printTotalBenefitPrice(discountResponse, freeGiftResponse);
        outputView.printExpectedPayment(orderResponse, discountResponse);
    }

    private void renderingBadgeResult(BadgeResponse badgeResponse) {
        outputView.printBadgeResult(badgeResponse);
    }
}
