package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class InputView {
    public static int readDate() {
        while(true) {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            try{
                int date = Integer.parseInt(Console.readLine().trim());
                EventValidator.isDateValid(date);
                return date;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Order> readOrder() {
        List<Order> orders = new ArrayList<>();
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        while(true) {
            try {
                String[] userOrders = Console.readLine().trim().split(",");
                for (String order : userOrders) {
                    EventValidator.validateOrderInput(order);
                    String[] orderParts = order.trim().split("-");
                    String menuName = orderParts[0].trim();
                    int menuQuantity = Integer.parseInt(orderParts[1].trim());
                    orders.add(new Order(menuName, menuQuantity));
                }
                return orders;
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                orders.clear();
            }
        }
    }

}
