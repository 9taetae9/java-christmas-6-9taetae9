package christmas;

public class Eventvalidator {
    public static void isDateValid(int date) {
        try {
            if (date < 1 || date > 31) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void isOrderValid(String order) {
        try{
            if(order==null || order.isEmpty()){
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            if(!order.contains("-")){
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean isMenuValid(String menu) {
        return "Main".equals(menu) || "Dessert".equals(menu);
    }

    public static boolean isQuantityValid(int quantity) {
        if(quantity<1){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
