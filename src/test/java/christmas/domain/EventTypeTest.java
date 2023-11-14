package christmas.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventTypeTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @DisplayName("할인 가능 이벤트 - 주말")
    void weekend(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKEND);
    }

    @ParameterizedTest
    @ValueSource(ints = {29, 30})
    @DisplayName("할인 가능 이벤트 - 주말만")
    void onlyWeekend(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(1);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKEND);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23})
    @DisplayName("할인 가능 이벤트 - 주말, 크리스마스")
    void weekendAndChristmas(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(2);
        Assertions.assertThat(applicableEventTypes).contains(EventType.CHRISTMAS);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKEND);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7,
            10, 11, 12, 13, 14,
            17, 18, 19, 20, 21,
            24, 25, 26, 27, 28,
            31})
    @DisplayName("할인 가능 이벤트 - 평일")
    void weekDay(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKDAY);
    }

    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28})
    @DisplayName("할인 가능 이벤트 - 평일만")
    void onlyWeekDay(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(1);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKDAY);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7,
            11, 12, 13, 14,
            18, 19, 20, 21})
    @DisplayName("할인 가능 이벤트 - 평일, 크리스마스")
    void weekDayAndChristmas(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(2);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKDAY);
        Assertions.assertThat(applicableEventTypes).contains(EventType.CHRISTMAS);
    }

    @ParameterizedTest
    @ValueSource(ints = {31})
    @DisplayName("할인 가능 이벤트 - 평일, 특별")
    void weekDayAndSpecial(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(2);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKDAY);
        Assertions.assertThat(applicableEventTypes).contains(EventType.SPECIAL);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25})
    @DisplayName("할인 가능 이벤트 - 평일, 특별, 크리스마스")
    void weekDayAndSpecialAndChristmas(int day) {
        List<EventType> applicableEventTypes = EventType.getApplicableEventTypes(day);
        Assertions.assertThat(applicableEventTypes).size().isEqualTo(3);
        Assertions.assertThat(applicableEventTypes).contains(EventType.WEEKDAY);
        Assertions.assertThat(applicableEventTypes).contains(EventType.SPECIAL);
        Assertions.assertThat(applicableEventTypes).contains(EventType.CHRISTMAS);
    }
}