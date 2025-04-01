package net.javademo.pms.service;

import lombok.AllArgsConstructor;
import net.javademo.pms.entity.Product;
import net.javademo.pms.exception.ResourceNotFoundException;
import net.javademo.pms.mapper.ProductMapper;
import net.javademo.pms.model.ProductModel;
import net.javademo.pms.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Override
    public ProductModel createProduct(ProductModel productModel) {

        Product product = ProductMapper.mapToProduct(productModel);
        Product savedProduct = productRepository.save(product);//save to database
        return ProductMapper.mapToProductModel(savedProduct);
    }

    @Override
    public ProductModel getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exists with given id: " + productId));
        return ProductMapper.mapToProductModel(product);
    }

    @Override
    public List<ProductModel> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((product) -> ProductMapper.mapToProductModel(product)).collect(Collectors.toList());
    }

    @Override
    public ProductModel updateProduct(Long productId, ProductModel updatedProductModel) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not exists with given id: " + productId)
        );
        product.setName(updatedProductModel.getName());
        product.setPrice(updatedProductModel.getPrice());
        product.setStock(updatedProductModel.getStock());
        Product updatedProductObj = productRepository.save(product);//save and update it to db
        //if no that id, perform insert operations
        return ProductMapper.mapToProductModel(updatedProductObj);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not exists with given id: " + productId)
        );

        productRepository.deleteById(productId);
    }
}
