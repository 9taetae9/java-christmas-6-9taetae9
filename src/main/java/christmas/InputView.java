package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

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
            try {
                String orderInputLine = Console.readLine();
                return EventValidator.validateAndParseOrders(orderInputLine);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
