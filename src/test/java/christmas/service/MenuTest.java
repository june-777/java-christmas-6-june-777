package christmas.service;

import christmas.domain.Menu;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @ParameterizedTest
    @ValueSource(strings = {"스파게티", "파스타", "쭈꾸미", "초밥"})
    @DisplayName("주문한 메뉴가 우테코 메뉴에 존재하지 않으면, Optionl의 값이 없다.")
    void notExistsMenu(String orderMenuName) {
        Optional<Menu> findMenu = Menu.find(orderMenuName);
        Assertions.assertThat(findMenu.isPresent()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드",
            "티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타",
            "초코케이크", "아이스크림",
            "제로콜라", "레드와인", "샴페인"})
    @DisplayName("주문한 메뉴가 우테코 메뉴에 존재하면, Optionl의 값이 있다.")
    void existsMenu(String orderMenuName) {
        Optional<Menu> findMenu = Menu.find(orderMenuName);
        Assertions.assertThat(findMenu.isPresent()).isTrue();
    }
}