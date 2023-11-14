package christmas;

class Order {
    private String itemName;
    private int quantity;
    private String category;

    public Order(String itemName, int quantity){
        this.itemName = itemName;
        this.quantity = quantity;
        this.category = Menu.getMenu(itemName).getCategory();
    }

    public String getItemName(){
        return itemName;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getCategory(){
        return category;
    }

    public int getTotalPrice(){
        return Menu.getMenu(itemName).getPrice() * quantity;
    }
}
