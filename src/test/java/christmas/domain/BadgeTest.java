package christmas.domain;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 4999})
    @DisplayName("총혜택 금액이 0원 ~ 4999원이면 배지가 없다.")
    void nothing(int totalBenefitAmount) {
        Optional<Badge> badge = Badge.calculateBadge(totalBenefitAmount);
        Assertions.assertThat(badge).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {5000, 5001, 6000, 7000, 8000, 9000, 9999})
    @DisplayName("총혜택 금액이 5,000원 ~ 9,999원이면 스타 배지이다.")
    void star(int totalBenefitAmount) {
        Optional<Badge> badge = Badge.calculateBadge(totalBenefitAmount);
        Assertions.assertThat(badge).isPresent();
        Assertions.assertThat(badge.get()).isEqualTo(Badge.STAR);
    }

    @ParameterizedTest
    @ValueSource(ints = {10000, 11000, 12000, 13030, 15678, 18989, 19999})
    @DisplayName("총혜택 금액이 10,000원 ~ 19,999원이면 트리 배지이다.")
    void tree(int totalBenefitAmount) {
        Optional<Badge> badge = Badge.calculateBadge(totalBenefitAmount);
        Assertions.assertThat(badge).isPresent();
        Assertions.assertThat(badge.get()).isEqualTo(Badge.TREE);
    }

    @ParameterizedTest
    @ValueSource(ints = {20000, 21000, 100000, 123123, 234232})
    @DisplayName("총혜택 금액이 20,000원 이상이면 트리 배지이다.")
    void santa(int totalBenefitAmount) {
        Optional<Badge> badge = Badge.calculateBadge(totalBenefitAmount);
        Assertions.assertThat(badge).isPresent();
        Assertions.assertThat(badge.get()).isEqualTo(Badge.SANTA);
    }
}