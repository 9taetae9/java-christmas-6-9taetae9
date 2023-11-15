package christmas;

import java.util.Arrays;
import java.util.List;

public class EventValidator {
    public static void isDateValid(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateOrderInput(String orderInput) {
        if (orderInput == null || orderInput.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        String[] parts = orderInput.trim().split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        String menuName = parts[0].trim();
        if(!isMenuNameValid(menuName)){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
        try {
            int menuQuantity = Integer.parseInt(parts[1].trim());
            if(!isQuantityValid(menuQuantity)){
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateNotOnlyOrderedBeverage(List<Order> orders) {
        boolean nonBeverageFound = orders.stream()
                .anyMatch(order -> !order.getMenuCategory().equalsIgnoreCase("Beverage"));
        if (!nonBeverageFound) {
            throw new IllegalArgumentException("[ERROR] 주문에는 음료만 포함될 수 없습니다. 다시 입력해 주세요.");
        }
    }

    private static boolean isMenuNameValid(String menu) {
        return Arrays.stream(Menu.values())
                .anyMatch(m -> Boolean.parseBoolean(m.getMenuname()));
    }

    private static boolean isQuantityValid(int quantity) {
        return quantity >= 1;
    }
}
