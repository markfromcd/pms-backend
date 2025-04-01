package net.javademo.pms.mapper;

import net.javademo.pms.entity.Product;
import net.javademo.pms.factory.ProductCategory;
import net.javademo.pms.factory.ProductCategoryFactory;
import net.javademo.pms.model.ProductModel;

public class ProductMapper {
    public static ProductModel mapToProductModel(Product product) {
        // Apply the factory pattern to get the discount rate
        if (product.getCategory() != null) {
            try {
                ProductCategory category = ProductCategoryFactory.createProductCategory(product.getCategory());
                product.setCategory(category.getCategoryName());
                product.setDescription(category.getCategoryDesc());
                product.setDiscountRate(category.getDiscountRate());
            } catch (IllegalArgumentException e) {
                // If no category matches, set to 0.0
                product.setDiscountRate(0.0);
            }
        }
        return new ProductModel(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getDescription(),
                product.getDiscountPrice()
        );
    }
    public static Product mapToProduct(ProductModel productModel) {
        return new Product(
                productModel.getId(),
                productModel.getName(),
                productModel.getPrice(),
                productModel.getStock(),
                productModel.getCategory(),
                productModel.getDescription()
        );
    }
}
