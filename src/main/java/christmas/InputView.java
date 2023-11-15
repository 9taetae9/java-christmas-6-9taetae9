package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputView {
    public static int readDate() {
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine().trim();

                if (!input.matches("\\d+")) {
                    throw new NumberFormatException();
                }
                int date = Integer.parseInt(input);
                EventValidator.isDateValid(date);
                return date;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Order> readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        while (true) {
            String orderInputLine = Console.readLine();
            try {
                return parseOrders(orderInputLine);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static List<Order> parseOrders(String orderInputLine) throws IllegalArgumentException {
        String[] userOrders = orderInputLine.trim().split(",");
        List<Order> orders = new ArrayList<>();
        Set<String> orderNames = new HashSet<>();

        for (String userOrder : userOrders) {
            String[] orderParts = userOrder.trim().split("-");
            if (orderParts.length != 2) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            String menuName = orderParts[0].trim();
            int menuQuantity = parseQuantity(orderParts[1].trim());

            if (!orderNames.add(menuName)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            orders.add(new Order(menuName, menuQuantity));
        }
        EventValidator.validateNotOnlyOrderedBeverage(orders);
        return orders;
    }

    private static int parseQuantity(String quantityStr) {
        try {
            return Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

}
