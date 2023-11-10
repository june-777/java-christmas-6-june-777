package christmas.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),

    T_BONE_STEAK("티본스테이크", 55000),
    BARBECUE_LIP("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),

    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),

    ZERO_COLA("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private static final Map<String, Menu> cachedMenu = new HashMap<>();

    private final String name;
    private final int price;

    Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    static {
        for (Menu menu : values()) {
            cachedMenu.put(menu.getName(), menu);
        }
    }

    public static Optional<Menu> find(String menuName) {
        return Optional.ofNullable(cachedMenu.get(menuName));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
