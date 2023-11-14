package christmas;

class Order {
    private String itemName;
    private int quantity;

    public Order(String itemName, int quantity, int price){
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName(){
        return itemName;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getTotalPrice(){
        return Menu.getMenu(itemName).getPrice() * quantity;
    }
}
