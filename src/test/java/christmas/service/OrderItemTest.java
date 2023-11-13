package christmas.service;

import christmas.domain.exception.MenuNotFoundException;
import christmas.domain.exception.OutOfRangeOrderItemCountException;
import christmas.domain.order.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OrderItemTest {

    @Nested
    @DisplayName("존재하지 않는 메뉴 주문 예외 테스트")
    class MenuNotFountExceptionTest {
        @ParameterizedTest
        @CsvSource(value = {
                "양송이수우프,1", "타파스!,10", "시저샐러드ㅋ,3",
                "티본 스테이크,2", "바비큐Leap,5", "씨푸드파스타,7", "산타파스타,4",
                "쵸코케이크,1", "아이쓰크림,3",
                "콜라,12", "화이트와인,11", "샴패인,13"})
        @DisplayName("[Exception] 존재하지 않는 메뉴이면 MenuNotFoundException이 발생한다.")
        void notExistMenuOne(String menuName, int orderCount) {
            Assertions.assertThatThrownBy(() -> new OrderItem(menuName, orderCount))
                    .isInstanceOf(MenuNotFoundException.class);
        }

        @ParameterizedTest
        @CsvSource(value = {
                "양송이수프,0", "타파스,21", "시저샐러드,125",
                "티본스테이크,122124", "바비큐립,23", "해산물파스타,37", "크리스마스파스타,45",
                "초코케이크,121", "아이스크림,30",
                "제로콜라,-30", "레드와인,-20", "샴페인,-1"})
        @DisplayName("[Exception] 주문 상품의 개수가 1 ~ 20개가 아니면 OutOfRangeOrderItemCountException 발생한다.")
        void outOfRangeItemCount(String menuName, int orderCount) {
            Assertions.assertThatThrownBy(() -> new OrderItem(menuName, orderCount))
                    .isInstanceOf(OutOfRangeOrderItemCountException.class);
        }
    }
}