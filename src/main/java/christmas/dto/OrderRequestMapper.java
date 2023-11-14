package christmas.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRequestMapper {

    private static final String menuSeparator = ",";
    private static final String menuAndCountSeparator = "-";
    private static final int menuAndCountLength = 2;
    private static final int menuIndex = 0;
    private static final int countIndex = 1;

    public static OrderRequest fromMenuForm(String menuForm, int day) {
        List<OrderItemRequest> orderItemRequests = Arrays.stream(menuForm.split(menuSeparator))
                .map(nameAndCount -> nameAndCount.split(menuAndCountSeparator))
                .filter(parts -> parts.length == menuAndCountLength)
                .map(parts -> new OrderItemRequest(parts[menuIndex], Integer.parseInt(parts[countIndex])))
                .collect(Collectors.toList());

        return new OrderRequest(orderItemRequests, day);
    }
}
