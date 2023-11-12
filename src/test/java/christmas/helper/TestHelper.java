package christmas.helper;

import christmas.dto.OrderMenu;
import java.util.List;
import java.util.stream.Stream;

public class TestHelper {

    public static Stream<List<OrderMenu>> createOrderNotExistMenu() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("", 1),
                new OrderMenu(" ", 2),
                new OrderMenu("7", 3),
                new OrderMenu("양송이스프", 10),
                new OrderMenu("타파쓰", 12),
                new OrderMenu("씨저샐러드", 7),
                new OrderMenu("TBornSteak", 1),
                new OrderMenu("바베큐Lip", 5),
                new OrderMenu("seafood pasta", 7),
                new OrderMenu("크리스마스 파스타", 4),
                new OrderMenu(" 초코케이크", 5),
                new OrderMenu("아이스크림 ", 6),
                new OrderMenu("제로콜ㄹ", 8),
                new OrderMenu("레드와인!", 9),
                new OrderMenu("샴페인\n", 11));

        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createNotExistAndDuplicateMenu() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("제로콜라", 1),
                new OrderMenu("크리스마스파스타", 2),
                new OrderMenu("샴페인", 3),
                new OrderMenu("", 4),
                new OrderMenu("크리스마스파스타", 4));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createNotExistAndOnlyBeverageMenu() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("제로콜라", 1),
                new OrderMenu("레드와인", 2),
                new OrderMenu("샴페인", 3),
                new OrderMenu("", 4));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createNotExistAndExceedCount() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("제로콜라", 1),
                new OrderMenu("레드 와인", 2),
                new OrderMenu("샴페인", 3),
                new OrderMenu("양송이수프", 1),
                new OrderMenu("티본스테이크", 10),
                new OrderMenu("초코케이크", 3),
                new OrderMenu("시저샐러드", 1));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createNotExistAndMix() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("샴페인", 1),
                new OrderMenu("레드 와인", 2),
                new OrderMenu("샴페인 ", 10),
                new OrderMenu("바비큐립", 10),
                new OrderMenu("바비큐립", 10));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createDuplicate() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("양송이수프", 1),
                new OrderMenu("해산물파스타", 2),
                new OrderMenu("아이스크림", 3),
                new OrderMenu("샴페인", 4),
                new OrderMenu("해산물파스타", 5));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createDuplicateAndOnlyBeverage() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("제로콜라", 1),
                new OrderMenu("레드와인", 2),
                new OrderMenu("레드와인", 3),
                new OrderMenu("샴페인", 4));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createDuplicateAndExceedCount() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("시저샐러드", 1),
                new OrderMenu("크리스마스파스타", 2),
                new OrderMenu("시저샐러드", 3),
                new OrderMenu("레드와인", 5));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createDuplicateAndMix() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("제로콜라", 10),
                new OrderMenu("레드와인", 2),
                new OrderMenu("제로콜라", 3),
                new OrderMenu("샴페인", 5));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createOnlyBeverage() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("제로콜라", 2),
                new OrderMenu("레드와인", 1),
                new OrderMenu("샴페인", 5));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createOnlyBeverageAndExceedCount() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("제로콜라", 10),
                new OrderMenu("레드와인", 10),
                new OrderMenu("샴페인", 1));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createExceedCount() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("양송이수프", 1),
                new OrderMenu("타파스", 1),
                new OrderMenu("시저샐러드", 1),
                new OrderMenu("티본스테이크", 1),
                new OrderMenu("바비큐립", 1),
                new OrderMenu("해산물파스타", 1),
                new OrderMenu("크리스마스파스타", 1),
                new OrderMenu("초코케이크", 1),
                new OrderMenu("아이스크림", 1),
                new OrderMenu("제로콜라", 1),
                new OrderMenu("레드와인", 1),
                new OrderMenu("샴페인", 10));
        return Stream.of(orderMenus);
    }

    public static Stream<List<OrderMenu>> createSuccessOrder() {
        List<OrderMenu> orderMenus = List.of(
                new OrderMenu("양송이수프", 1),
                new OrderMenu("타파스", 1),
                new OrderMenu("시저샐러드", 1),
                new OrderMenu("티본스테이크", 1),
                new OrderMenu("바비큐립", 1),
                new OrderMenu("해산물파스타", 1),
                new OrderMenu("크리스마스파스타", 1),
                new OrderMenu("초코케이크", 1),
                new OrderMenu("아이스크림", 1),
                new OrderMenu("제로콜라", 1),
                new OrderMenu("레드와인", 1),
                new OrderMenu("샴페인", 9));
        return Stream.of(orderMenus);
    }
}
