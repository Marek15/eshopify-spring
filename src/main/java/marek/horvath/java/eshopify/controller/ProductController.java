package marek.horvath.java.eshopify.controller;

import marek.horvath.java.eshopify.entity.Product;
import marek.horvath.java.eshopify.dto.ProductDto;
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
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody ProductDto product) {
        return productService.createProduct(product);
    }

}
