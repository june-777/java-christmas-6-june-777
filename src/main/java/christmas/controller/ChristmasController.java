package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.event.DiscountInformation;
import christmas.domain.event.FreeGift;
import christmas.domain.order.Order;
import christmas.domain.order.OrderDay;
import christmas.dto.request.OrderRequest;
import christmas.dto.response.BadgeResponse;
import christmas.dto.response.DiscountResponse;
import christmas.dto.response.FreeGiftResponse;
import christmas.service.badge.BadgeService;
import christmas.service.event.EventService;
import christmas.service.order.OrderService;
import christmas.utils.mapper.BadgeResponseMapper;
import christmas.utils.mapper.DiscountResponseMapper;
import christmas.utils.mapper.FreeGiftResponseMapper;
import java.util.Optional;

public class ChristmasController {

    private final OrderService orderService;
    private final EventService eventService;
    private final BadgeService badgeService;

    public ChristmasController(OrderService orderService, EventService eventService, BadgeService badgeService) {
        this.orderService = orderService;
        this.eventService = eventService;
        this.badgeService = badgeService;
    }

    public OrderDay createOrderDay(final int date) {
        return new OrderDay(date);
    }

    public Order createOrder(final OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    public DiscountResponse discountEvent(final Order order) {
        final Optional<DiscountInformation> discountInformation = eventService.applyDiscountEvent(order);

        return discountInformation.map(DiscountResponseMapper::of)
                .orElseGet(DiscountResponseMapper::of);
    }

    public FreeGiftResponse giftEvent(final Order order) {
        final Optional<FreeGift> freeGift = eventService.applyFreeGiftEvent(order);

        return freeGift.map(FreeGiftResponseMapper::of)
                .orElseGet(FreeGiftResponseMapper::of);
    }

    public BadgeResponse badge(final int totalDiscountAmount, final int freeGiftPrice) {
        final Optional<Badge> badge = badgeService.applyBadge(totalDiscountAmount + freeGiftPrice);

        return badge.map(BadgeResponseMapper::of)
                .orElseGet(BadgeResponseMapper::of);
    }
}
