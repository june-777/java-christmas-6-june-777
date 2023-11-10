package christmas.helper;

import christmas.dto.OrderMenu;
import java.util.List;
import java.util.stream.Stream;

public class TestHelper {

    public static Stream<List<OrderMenu>> createOrderNotExistMenu() {
        List<OrderMenu> orderMenus = List.of(new OrderMenu("", 1),
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
}
