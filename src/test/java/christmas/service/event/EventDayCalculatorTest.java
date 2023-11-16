package christmas.service.event;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventDayCalculatorTest {
    
    @Test
    @DisplayName("크리스마스 디데이 할인 날짜 테스트")
    void christmasDay() {
        // given
        List<Integer> days = EventDayCalculator.christmasDay();
        // when, then
        Assertions.assertThat(days)
                .contains(1, 2, 3, 4, 5, 6, 7, 8,
                        9, 10, 11, 12, 13, 14, 15,
                        16, 17, 18, 19, 20, 21, 22,
                        23, 24, 25)
                .size().isEqualTo(25);
    }

    @Test
    @DisplayName("평일 할인 날짜 테스트")
    void weekDay() {
        // given
        List<Integer> days = EventDayCalculator.weekDay();
        // when, then
        Assertions.assertThat(days)
                .contains(3, 4, 5, 6, 7,
                        10, 11, 12, 13, 14,
                        17, 18, 19, 20, 21,
                        24, 25, 26, 27, 28, 31)
                .size().isEqualTo(21);
    }

    @Test
    @DisplayName("주말 할인 날짜 테스트")
    void weekEnd() {
        // given
        List<Integer> days = EventDayCalculator.weekEnd();
        // when, then
        Assertions.assertThat(days)
                .contains(1, 2, 8, 9,
                        15, 16, 22, 23,
                        29, 30)
                .size().isEqualTo(10);
    }

    @Test
    @DisplayName("특별 할인 날짜 테스트")
    void specialDay() {
        // given
        List<Integer> days = EventDayCalculator.specialDay();
        // when, then
        Assertions.assertThat(days)
                .contains(3, 10, 17, 24, 25, 31)
                .size().isEqualTo(6);
    }
}