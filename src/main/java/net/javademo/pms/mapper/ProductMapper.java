package net.javademo.pms.mapper;

import net.javademo.pms.entity.Product;
import net.javademo.pms.model.ProductModel;

public class ProductMapper {
    public static ProductModel mapToProductModel(Product product) {
        return new ProductModel(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
    public static Product mapToProduct(ProductModel productModel) {
        return new Product(
                productModel.getId(),
                productModel.getName(),
                productModel.getPrice(),
                productModel.getStock()
        );
    }
}
