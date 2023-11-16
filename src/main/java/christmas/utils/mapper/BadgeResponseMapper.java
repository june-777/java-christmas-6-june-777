package christmas.utils.mapper;

import christmas.domain.Badge;
import christmas.dto.response.BadgeResponse;

public class BadgeResponseMapper {

    private static final String EMPTY = "없음";

    public static BadgeResponse of(Badge badge) {
        return new BadgeResponse(badge.getName());
    }

    public static BadgeResponse of() {
        return new BadgeResponse(EMPTY);
    }
}
