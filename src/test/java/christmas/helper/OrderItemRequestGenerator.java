package christmas.helper;

import christmas.dto.OrderItemRequest;
import java.util.List;
import java.util.stream.Stream;

public class OrderItemRequestGenerator {
    public static Stream<List<OrderItemRequest>> createOrderNotExistMenu() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("", 1),
                new OrderItemRequest(" ", 2),
                new OrderItemRequest("7", 3),
                new OrderItemRequest("양송이스프", 10),
                new OrderItemRequest("타파쓰", 12),
                new OrderItemRequest("씨저샐러드", 7),
                new OrderItemRequest("TBornSteak", 1),
                new OrderItemRequest("바베큐Lip", 5),
                new OrderItemRequest("seafood pasta", 7),
                new OrderItemRequest("크리스마스 파스타", 4),
                new OrderItemRequest(" 초코케이크", 5),
                new OrderItemRequest("아이스크림 ", 6),
                new OrderItemRequest("제로콜ㄹ", 8),
                new OrderItemRequest("레드와인!", 9),
                new OrderItemRequest("샴페인\n", 11));

        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createNotExistAndDuplicateMenu() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("제로콜라", 1),
                new OrderItemRequest("크리스마스파스타", 2),
                new OrderItemRequest("샴페인", 3),
                new OrderItemRequest("", 4),
                new OrderItemRequest("크리스마스파스타", 4));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createNotExistAndOnlyBeverageMenu() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("제로콜라", 1),
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("샴페인", 3),
                new OrderItemRequest("", 4));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createNotExistAndExceedCount() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("제로콜라", 1),
                new OrderItemRequest("레드 와인", 2),
                new OrderItemRequest("샴페인", 3),
                new OrderItemRequest("양송이수프", 1),
                new OrderItemRequest("티본스테이크", 10),
                new OrderItemRequest("초코케이크", 3),
                new OrderItemRequest("시저샐러드", 1));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createNotExistAndMix() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("샴페인", 1),
                new OrderItemRequest("레드 와인", 2),
                new OrderItemRequest("샴페인 ", 10),
                new OrderItemRequest("바비큐립", 10),
                new OrderItemRequest("바비큐립", 10));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createOutOfRangeLessMin() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("샴페인", 1),
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("해산물파스타", 3),
                new OrderItemRequest("바비큐립", 4),
                new OrderItemRequest("크리스마스파스타", 0));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createOutOfRangeLessMinAndDuplicate() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("샴페인", 1),
                new OrderItemRequest("양송이수프", 2),
                new OrderItemRequest("해산물파스타", 3),
                new OrderItemRequest("바비큐립", 4),
                new OrderItemRequest("티본스테이크", 0),
                new OrderItemRequest("티본스테이크", 7));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createOutOfRangeGreaterMax() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("초코케이크", 1),
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("아이스크림", 3),
                new OrderItemRequest("바비큐립", 4),
                new OrderItemRequest("제로콜라", 21));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createOutOfRangeGreaterMaxAndOnlyBeverage() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("제로콜라", 3),
                new OrderItemRequest("샴페인", 4),
                new OrderItemRequest("제로콜라", 21));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createDuplicate() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("초코케이크", 1),
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("아이스크림", 3),
                new OrderItemRequest("바비큐립", 4),
                new OrderItemRequest("초코케이크", 7));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createOnlyBeverage() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("제로콜라", 1),
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("샴페인", 7));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItemRequest>> createExceedTotalOrderCount() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("양송이수프", 1),
                new OrderItemRequest("바비큐립", 2),
                new OrderItemRequest("초코케이크", 3),
                new OrderItemRequest("레드와인", 4),
                new OrderItemRequest("샴페인", 5),
                new OrderItemRequest("크리스마스파스타", 5),
                new OrderItemRequest("해산물파스타", 1));
        return Stream.of(orderItems);
    }
}
