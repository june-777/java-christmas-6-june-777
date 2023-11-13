package christmas.helper;

import christmas.domain.order.OrderItem;
import java.util.List;
import java.util.stream.Stream;

public class TestHelper {

    public static Stream<List<OrderItem>> createDuplicate() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("양송이수프", 1),
                new OrderItem("해산물파스타", 2),
                new OrderItem("아이스크림", 3),
                new OrderItem("샴페인", 4),
                new OrderItem("해산물파스타", 5));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItem>> createDuplicateAndOnlyBeverage() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("제로콜라", 1),
                new OrderItem("레드와인", 2),
                new OrderItem("레드와인", 3),
                new OrderItem("샴페인", 4));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItem>> createDuplicateAndExceedCount() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("시저샐러드", 1),
                new OrderItem("크리스마스파스타", 2),
                new OrderItem("시저샐러드", 3),
                new OrderItem("레드와인", 5));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItem>> createDuplicateAndMix() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("제로콜라", 10),
                new OrderItem("레드와인", 2),
                new OrderItem("제로콜라", 3),
                new OrderItem("샴페인", 5));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItem>> createOnlyBeverage() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("제로콜라", 2),
                new OrderItem("레드와인", 1),
                new OrderItem("샴페인", 5));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItem>> createOnlyBeverageAndExceedCount() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("제로콜라", 10),
                new OrderItem("레드와인", 10),
                new OrderItem("샴페인", 1));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItem>> createExceedCount() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("양송이수프", 1),
                new OrderItem("타파스", 1),
                new OrderItem("시저샐러드", 1),
                new OrderItem("티본스테이크", 1),
                new OrderItem("바비큐립", 1),
                new OrderItem("해산물파스타", 1),
                new OrderItem("크리스마스파스타", 1),
                new OrderItem("초코케이크", 1),
                new OrderItem("아이스크림", 1),
                new OrderItem("제로콜라", 1),
                new OrderItem("레드와인", 1),
                new OrderItem("샴페인", 10));
        return Stream.of(orderItems);
    }

    public static Stream<List<OrderItem>> createSuccessOrder() {
        List<OrderItem> orderItems = List.of(
                new OrderItem("양송이수프", 1),
                new OrderItem("타파스", 1),
                new OrderItem("시저샐러드", 1),
                new OrderItem("티본스테이크", 1),
                new OrderItem("바비큐립", 1),
                new OrderItem("해산물파스타", 1),
                new OrderItem("크리스마스파스타", 1),
                new OrderItem("초코케이크", 1),
                new OrderItem("아이스크림", 1),
                new OrderItem("제로콜라", 1),
                new OrderItem("레드와인", 1),
                new OrderItem("샴페인", 9));
        return Stream.of(orderItems);
    }
}
