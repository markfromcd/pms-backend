package net.javademo.pms.factory;

public class GroceryProduct implements ProductCategory{
    @Override
    public String getCategoryName() {
        return "Grocery";
    }

    @Override
    public String getCategoryDesc() {
        return "Food, drinks and grocery items";
    }

    @Override
    public double getDiscountRate() {
        return 0.05;
    }
}
