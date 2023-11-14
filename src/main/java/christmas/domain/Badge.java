package christmas.domain;

import java.util.Optional;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final int minTotalBenefitAmount;

    Badge(String name, int minTotalBenefitAmount) {
        this.name = name;
        this.minTotalBenefitAmount = minTotalBenefitAmount;
    }

    public static Optional<Badge> calculateBadge(int totalBenefitAmount) {
        totalBenefitAmount = Math.abs(totalBenefitAmount);
        if (isSatisfyStarCondition(totalBenefitAmount)) {
            return Optional.of(STAR);
        }
        if (isSatisfyTreeCondition(totalBenefitAmount)) {
            return Optional.of(TREE);
        }
        if (isSatisfySantaCondition(totalBenefitAmount)) {
            return Optional.of(SANTA);
        }
        return Optional.empty();
    }

    private static boolean isSatisfyStarCondition(int totalBenefitAmount) {
        return STAR.minTotalBenefitAmount <= totalBenefitAmount && totalBenefitAmount < TREE.minTotalBenefitAmount;
    }

    private static boolean isSatisfyTreeCondition(int totalBenefitAmount) {
        return TREE.minTotalBenefitAmount <= totalBenefitAmount && totalBenefitAmount < SANTA.minTotalBenefitAmount;
    }

    private static boolean isSatisfySantaCondition(int totalBenefitAmount) {
        return SANTA.minTotalBenefitAmount <= totalBenefitAmount;
    }

    public String getName() {
        return name;
    }
}
