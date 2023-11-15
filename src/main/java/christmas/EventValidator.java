package christmas;

import java.util.Arrays;

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
            throw new IllegalArgumentException("[ERROR] 주문 형식이 잘못되었습니다.");
        }
        String itemName = parts[0].trim();
        int quantity;
        try {
            quantity = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 수량은 숫자로 입력해 주세요.");
        }

        if (!isMenuNameValid(itemName) || !isQuantityValid(quantity)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static boolean isMenuNameValid(String menu) {
        return Arrays.stream(Menu.values())
                .anyMatch(m -> m.getName().equalsIgnoreCase(menu));
    }

    private static boolean isQuantityValid(int quantity) {
        return quantity >= 1;
    }
}
