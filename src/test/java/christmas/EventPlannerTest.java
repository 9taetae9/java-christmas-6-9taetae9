package christmas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventPlannerTest {

    @Test
    public void testCalculateTotalDiscountWithoutChampagne_WithValidInput() {
        int date = 15;
        List<Order> orders = List.of(
                new Order("양송이수프", 2),
                new Order("타파스", 1)
        );

        int totalDiscount = DiscountCalculator.calculateTotalDiscountWithoutChampagne(date, orders);

        int expectedDiscount = 2400;
        assertEquals(expectedDiscount, totalDiscount);
    }

    @Test
    void calculateTotalDiscountWithNoOrders_ShouldReturnZero() {
        int date = 10;
        List<Order> orders = Collections.emptyList();

        int totalDiscount = DiscountCalculator.calculateTotalDiscount(date, orders);
        assertEquals(0, totalDiscount, "Total discount should be zero when there are no orders.");
    }


    @Test
    void constructDiscountDetailsWithNoApplicableDiscounts_ShouldReturnNone() {
        int date = 5;
        List<Order> orders = List.of(
                new Order("제로콜라", 2)
        );

        String discountDetails = DiscountCalculator.constructDiscountDetails(date, orders);
        assertEquals("없음", discountDetails, "Discount details should return '없음' if no discounts are applicable.");
    }

    @Test
    void qualifiesForChampagneWithHighAmount_ShouldReturnTrue() {
        List<Order> orders = List.of(
                new Order("티본스테이크", 3) // High amount order
        );

        assertTrue(DiscountCalculator.qualifiesForChampagne(DiscountCalculator.totalOrderAmount(orders)),
                "Qualifies for Champagne should return true for high amount orders.");
    }

    @Test
    void getBadgeByDiscountWithEnoughDiscount_ShouldReturnCorrectBadge() {
        int discount = 15000;
        int totalOrderAmount = 20000;

        Badge badge = Badge.getBadgeByDiscount(discount, totalOrderAmount);
        assertEquals(Badge.TREE, badge, "Get badge by discount should return the correct badge based on the discount amount.");
    }
}
