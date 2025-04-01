package net.javademo.pms.service;

import net.javademo.pms.entity.Product;
import net.javademo.pms.model.ProductModel;
import java.util.*;


public interface ProductService {
    ProductModel createProduct(ProductModel productModel);

    ProductModel getProductById(Long productId);

    List<ProductModel> getAllProducts();

    ProductModel updateProduct(Long productId, ProductModel updatedProductModel);

    void deleteProduct(Long productId);
}
