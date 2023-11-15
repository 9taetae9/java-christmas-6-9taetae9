package christmas;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        OutputView.greeting();

        int visitDate = InputView.readDate();
        List<Order> orders = InputView.readOrder();

        OutputView.printMenu(orders);
        OutputView.printOrderDetails(visitDate, orders);
    }
}
