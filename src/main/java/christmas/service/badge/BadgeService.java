package christmas.service.badge;

import christmas.domain.Badge;
import java.util.Optional;

public class BadgeService {
    public Optional<Badge> applyBadge(int totalBenefitAmount) {
        return Badge.calculateBadge(totalBenefitAmount);
    }
}
