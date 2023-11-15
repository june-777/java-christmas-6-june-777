package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.event.DiscountInformation;
import christmas.domain.event.FreeGift;
import christmas.domain.order.Order;
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

    public Order createOrder(OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }

    public DiscountResponse discountEvent(Order order) {
        Optional<DiscountInformation> discountInformation = eventService.applyDiscountEvent(order);

        return discountInformation.map(DiscountResponseMapper::of)
                .orElseGet(DiscountResponseMapper::of);
    }

    public FreeGiftResponse giftEvent(Order order) {
        Optional<FreeGift> freeGift = eventService.applyFreeGiftEvent(order);

        return freeGift.map(FreeGiftResponseMapper::of)
                .orElseGet(FreeGiftResponseMapper::of);
    }

    public BadgeResponse badge(int totalDiscountAmount, int freeGiftPrice) {
        Optional<Badge> badge = badgeService.applyBadge(totalDiscountAmount + freeGiftPrice);

        return badge.map(BadgeResponseMapper::of)
                .orElseGet(BadgeResponseMapper::of);
    }
}
