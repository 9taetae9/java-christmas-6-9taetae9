package christmas;

import java.util.*;

public class EventValidator {
    public static void isDateValid(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static List<Order> validateAndParseOrders(String orderInputLine) {
        if (orderInputLine == null || orderInputLine.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        String[] userOrders = orderInputLine.trim().split(",");
        Set<String> uniqueMenuNames = new HashSet<>();
        List<Order> orders = new ArrayList<>();

        for (String userOrder : userOrders) {
            String[] parts = userOrder.trim().split("-");
            if (parts.length != 2) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            String menuName = parts[0].trim();
            if (!uniqueMenuNames.add(menuName)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            if (!isMenuNameValid(menuName)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            int quantity = parseQuantity(parts[1].trim());
            orders.add(new Order(menuName, quantity));
        }

        validateNotOnlyOrderedBeverage(orders);

        return orders;
    }

    private static boolean isMenuNameValid(String menu) {
        return Arrays.stream(Menu.values())
                .anyMatch(m -> m.getMenuname().equalsIgnoreCase(menu));
    }

    private static int parseQuantity(String quantityStr) {
        try {
            int quantity = Integer.parseInt(quantityStr);
            if (quantity < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            return quantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateNotOnlyOrderedBeverage(List<Order> orders) {
        boolean nonBeverageFound = orders.stream()
                .anyMatch(order -> !order.getMenuCategory().equalsIgnoreCase("Beverage"));
        if (!nonBeverageFound) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
