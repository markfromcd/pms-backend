package net.javademo.pms.factory;

public class ProductCategoryFactory {

    public static ProductCategory createProductCategory(String categoryType) {
        switch (categoryType.toLowerCase()) {
            case "electronics":
                return new ElectronicsProduct();
            case "clothing":
                return new ClothesProduct();
            case "grocery":
                return new GroceryProduct();
            default:
                throw new IllegalArgumentException("Unkonwn category type: " + categoryType);
        }
    }
}
