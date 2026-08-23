package marek.horvath.java.eshopify.services;

import marek.horvath.java.eshopify.database.entity.Product;
import marek.horvath.java.eshopify.dto.CreateProductDto;
import marek.horvath.java.eshopify.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public Product createProduct(CreateProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.name());
        product.setPrice(productDto.price());
        product.setDescription(productDto.description());
        return this.productRepository.save(product);
    }
}
