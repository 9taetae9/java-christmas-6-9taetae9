package christmas;

import camp.nextstep.edu.missionutils.Console;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    public static int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String date = Console.readLine();
        Eventvalidator.isDateValid(Integer.parseInt(date));
        return Integer.parseInt(date);
    }
    public static List<Order> readOrder() {
        List<Order> orders = new ArrayList<>();
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String order = Console.readLine();
        Eventvalidator.isOrderValid(order);
        String[] ordersInputs = order.split(",");
        for(String orderInputs : ordersInputs){
            String[] parts = orderInputs.trim().split("-");
            String itemName = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());
            orders.add(new Order(itemName, quantity));
        }
        return orders;
    }

}
