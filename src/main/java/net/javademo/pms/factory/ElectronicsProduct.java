package net.javademo.pms.factory;

public class ElectronicsProduct implements ProductCategory{
    @Override
    public String getCategoryName() {
        return "Electronics";
    }

    @Override
    public String getCategoryDesc() {
        return "Electronic devices and accessories";
    }

    @Override
    public double getDiscountRate() {
        return 0.10;
    }// Create concrete class

}
