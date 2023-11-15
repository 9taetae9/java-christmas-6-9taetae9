package christmas;

class Order {
    private String menuName;
    private int menuQuantity;
    private String menuCategory;

    public Order(String itemName, int quantity){
        this.menuName = itemName;
        this.menuQuantity = quantity;
        this.menuCategory = Menu.getMenu(itemName).getCategory();
    }

    public String getMenuName(){
        return menuName;
    }

    public int getMenuQuantity(){
        return menuQuantity;
    }

    public String getMenuCategory(){
        return menuCategory;
    }

    public int getTotalPrice(){
        return Menu.getMenu(menuName).getPrice() * menuQuantity;
    }
}
