package christmas.helper;

import christmas.dto.OrderItemRequest;
import christmas.dto.OrderRequest;
import java.util.List;
import java.util.stream.Stream;

public class OrderRequestGenerator {
    public static Stream<OrderRequest> createOrderNotExistMenu() {
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
        OrderRequest orderRequest = new OrderRequest(orderItems, 1);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createNotExistAndDuplicateMenu() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("제로콜라", 1),
                new OrderItemRequest("크리스마스파스타", 2),
                new OrderItemRequest("샴페인", 3),
                new OrderItemRequest("", 4),
                new OrderItemRequest("크리스마스파스타", 4));
        OrderRequest orderRequest = new OrderRequest(orderItems, 31);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createNotExistAndOnlyBeverageMenu() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("제로콜라", 1),
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("샴페인", 3),
                new OrderItemRequest("", 4));
        OrderRequest orderRequest = new OrderRequest(orderItems, 30);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createNotExistAndExceedCount() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("제로콜라", 1),
                new OrderItemRequest("레드 와인", 2),
                new OrderItemRequest("샴페인", 3),
                new OrderItemRequest("양송이수프", 1),
                new OrderItemRequest("티본스테이크", 10),
                new OrderItemRequest("초코케이크", 3),
                new OrderItemRequest("시저샐러드", 1));
        OrderRequest orderRequest = new OrderRequest(orderItems, 25);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createNotExistAndMix() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("샴페인", 1),
                new OrderItemRequest("레드 와인", 2),
                new OrderItemRequest("샴페인 ", 10),
                new OrderItemRequest("바비큐립", 10),
                new OrderItemRequest("바비큐립", 10));
        OrderRequest orderRequest = new OrderRequest(orderItems, 15);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createOutOfRangeLessMin() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("샴페인", 1),
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("해산물파스타", 3),
                new OrderItemRequest("바비큐립", 4),
                new OrderItemRequest("크리스마스파스타", 0));
        OrderRequest orderRequest = new OrderRequest(orderItems, 15);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createOutOfRangeLessMinAndDuplicate() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("샴페인", 1),
                new OrderItemRequest("양송이수프", 2),
                new OrderItemRequest("해산물파스타", 3),
                new OrderItemRequest("바비큐립", 4),
                new OrderItemRequest("티본스테이크", 0),
                new OrderItemRequest("티본스테이크", 7));
        OrderRequest orderRequest = new OrderRequest(orderItems, 15);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createOutOfRangeGreaterMax() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("초코케이크", 1),
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("아이스크림", 3),
                new OrderItemRequest("바비큐립", 4),
                new OrderItemRequest("제로콜라", 21));
        OrderRequest orderRequest = new OrderRequest(orderItems, 15);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createOutOfRangeGreaterMaxAndOnlyBeverage() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("제로콜라", 3),
                new OrderItemRequest("샴페인", 4),
                new OrderItemRequest("제로콜라", 21));
        OrderRequest orderRequest = new OrderRequest(orderItems, 15);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createDuplicate() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("초코케이크", 1),
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("아이스크림", 3),
                new OrderItemRequest("바비큐립", 4),
                new OrderItemRequest("초코케이크", 7));
        OrderRequest orderRequest = new OrderRequest(orderItems, 15);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createOnlyBeverage() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("제로콜라", 1),
                new OrderItemRequest("레드와인", 2),
                new OrderItemRequest("샴페인", 7));
        OrderRequest orderRequest = new OrderRequest(orderItems, 15);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createExceedTotalOrderCount() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("양송이수프", 1),
                new OrderItemRequest("바비큐립", 2),
                new OrderItemRequest("초코케이크", 3),
                new OrderItemRequest("레드와인", 4),
                new OrderItemRequest("샴페인", 5),
                new OrderItemRequest("크리스마스파스타", 5),
                new OrderItemRequest("해산물파스타", 1));
        OrderRequest orderRequest = new OrderRequest(orderItems, 15);
        return Stream.of(orderRequest);
    }

    public static Stream<OrderRequest> createSuccess() {
        List<OrderItemRequest> orderItems = List.of(
                new OrderItemRequest("양송이수프", 1),
                new OrderItemRequest("타파스", 1),
                new OrderItemRequest("시저샐러드", 1),
                new OrderItemRequest("티본스테이크", 1),
                new OrderItemRequest("바비큐립", 1),
                new OrderItemRequest("해산물파스타", 1),
                new OrderItemRequest("크리스마스파스타", 1),
                new OrderItemRequest("초코케이크", 1),
                new OrderItemRequest("아이스크림", 1),
                new OrderItemRequest("제로콜라", 1),
                new OrderItemRequest("레드와인", 1),
                new OrderItemRequest("샴페인", 9));
        OrderRequest orderRequest = new OrderRequest(orderItems, 15);
        return Stream.of(orderRequest);
    }
}
