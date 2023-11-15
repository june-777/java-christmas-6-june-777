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
        final OrderDay orderDay = dateSetting();
        final Order order = orderSetting(orderDay.getDay());
        final OrderResponse orderResponse = OrderResponseMapper.fromOrder(order);

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

    public Order orderSetting(final int date) {
        return ExceptionHandler.handle(() -> {
            String menuForm = inputView.readMenuForm();
            OrderRequest orderRequest = OrderRequestMapper.fromMenuForm(menuForm, date);
            return christmasController.createOrder(orderRequest);
        });
    }

    private void renderingPreview(final OrderDay orderDay, final OrderResponse orderResponse) {
        outputView.printPreviewEventMessage(orderDay.getDay());
        outputView.printOrderMenu(orderResponse);
        outputView.printTotalOrderAmount(orderResponse);
    }

    private void processChristmasPromotion(final Order order, final OrderResponse orderResponse) {
        final DiscountResponse discountResponse = christmasController.discountEvent(order);
        final FreeGiftResponse freeGiftResponse = christmasController.giftEvent(order);
        renderingEventResult(orderResponse, freeGiftResponse, discountResponse);

        final BadgeResponse badgeResponse = christmasController.badge(discountResponse.getTotalDiscountAmount(),
                freeGiftResponse.getBenefitPrice());
        renderingBadgeResult(badgeResponse);
    }

    private void renderingEventResult(final OrderResponse orderResponse, final FreeGiftResponse freeGiftResponse,
                                      final DiscountResponse discountResponse) {
        outputView.printFreeGiftResult(freeGiftResponse);
        outputView.printBenefitResult(discountResponse, freeGiftResponse);
        outputView.printTotalBenefitPrice(discountResponse, freeGiftResponse);
        outputView.printExpectedPayment(orderResponse, discountResponse);
    }

    private void renderingBadgeResult(final BadgeResponse badgeResponse) {
        outputView.printBadgeResult(badgeResponse);
    }
}
