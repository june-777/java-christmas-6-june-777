package christmas.service.badge;

import christmas.domain.Badge;
import java.util.Optional;

public class BadgeService {
    public Optional<Badge> applyBadge(final int totalBenefitAmount) {
        return Badge.calculateBadge(totalBenefitAmount);
    }
}
