package christmas.utils.mapper;

import christmas.domain.event.FreeGift;
import christmas.dto.response.FreeGiftResponse;

public class FreeGiftResponseMapper {

    private static final String EMPTY = "없음";

    public static FreeGiftResponse of(FreeGift freeGift) {
        return new FreeGiftResponse(freeGift.getGiftName(), freeGift.calculateBenefitPrice(), freeGift.getCount());
    }

    public static FreeGiftResponse of() {
        return new FreeGiftResponse(EMPTY, 0, 0);
    }
}
