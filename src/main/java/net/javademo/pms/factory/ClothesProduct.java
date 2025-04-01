package net.javademo.pms.factory;

public class ClothesProduct implements ProductCategory{
    @Override
    public String getCategoryName() {
        return "Clothing";
    }

    @Override
    public String getCategoryDesc() {
        return "Apparel and fashion items";
    }

    @Override
    public double getDiscountRate() {
        return 0.15;// 15% discount
    }
}
