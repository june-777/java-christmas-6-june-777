package christmas.configuration;

import christmas.controller.ChristmasController;
import christmas.controller.FrontController;
import christmas.service.badge.BadgeService;
import christmas.service.event.EventService;
import christmas.service.event.discount.DiscountEventService;
import christmas.service.event.gift.FreeGiftEventService;
import christmas.service.order.OrderService;
import christmas.view.ConsoleReader;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.Reader;

public class ApplicationConfiguration {

    public FrontController frontController() {
        return new FrontController(christmasController(), inputView(), outputView());
    }

    private ChristmasController christmasController() {
        return new ChristmasController(orderService(), eventService(), badgeService());
    }

    private OrderService orderService() {
        return new OrderService();
    }

    private EventService eventService() {
        return new EventService(discountEventService(), giftEventService());
    }

    private DiscountEventService discountEventService() {
        return new DiscountEventService();
    }

    private FreeGiftEventService giftEventService() {
        return new FreeGiftEventService();
    }

    private BadgeService badgeService() {
        return new BadgeService();
    }

    private InputView inputView() {
        return new InputView(consoleReader());
    }

    private Reader consoleReader() {
        return new ConsoleReader();
    }

    private OutputView outputView() {
        return new OutputView();
    }

}
