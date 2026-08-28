package marek.horvath.java.eshopify.controller;

import marek.horvath.java.eshopify.entity.Product;
import marek.horvath.java.eshopify.dto.ProductDto;
import marek.horvath.java.eshopify.dto.ProductResponseDto;
import marek.horvath.java.eshopify.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ProductResponseDto getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody ProductDto product) {
        return productService.createProduct(product);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable UUID id, @RequestBody ProductDto product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
    }
}
