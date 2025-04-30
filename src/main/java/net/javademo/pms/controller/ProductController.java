package net.javademo.pms.controller;

import lombok.AllArgsConstructor;
import net.javademo.pms.model.ProductModel;
import net.javademo.pms.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@CrossOrigin(origins = {
        "*"
})
public class ProductController {

    private ProductService productService;

    // Build Add Product REST API
    @PostMapping //to map incoming HTTP reuqest
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel productModel) {//json to java object by @RequestBody
        ProductModel savedProduct = productService.createProduct(productModel);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Build Get Product REST API
    @GetMapping("{id}")//pass a id, called url template variable, bind with productId argument
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") Long productId) {
        ProductModel productModel = productService.getProductById(productId);
        return ResponseEntity.ok(productModel);
    }

    // Build Get All Products REST API
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Build Update Product REST API
    @PutMapping("{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable("id") Long productId,
                                                      @RequestBody ProductModel updatedProductModel) {
        ProductModel productModel = productService.updateProduct(productId, updatedProductModel);
        return ResponseEntity.ok(productModel);
    }

    //Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);

//        return ResponseEntity.ok("Product deleted successfully!");
        return ResponseEntity.noContent().build();
    }

    //Build Patch Product REST API
    @PatchMapping("{id}")
    public ResponseEntity<ProductModel> patchProduct(@PathVariable("id") Long productId,
                                                     @RequestBody Map<String, Object> fields) {
        ProductModel productModel = productService.patchProduct(productId, fields);
        return ResponseEntity.ok(productModel);
    }

}
