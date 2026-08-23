package marek.horvath.java.eshopify.services;

import jakarta.transaction.Transactional;
import marek.horvath.java.eshopify.entity.Product;
import marek.horvath.java.eshopify.dto.ProductDto;
import marek.horvath.java.eshopify.entity.ProductData;
import marek.horvath.java.eshopify.repository.ProductDataRepository;
import marek.horvath.java.eshopify.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDataRepository productDataRepository;

    public ProductService(ProductRepository productRepository, ProductDataRepository productDataRepository) {
        this.productRepository = productRepository;
        this.productDataRepository = productDataRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return this.productRepository.findById(id).orElse(null);
    }

    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        ProductData productData = new ProductData();
        UUID productDataId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        product.setId(productId);
        product.setProductDataId(productDataId);
        productData.setId(productDataId);
        productData.setProduct(product);
        productData.setName(productDto.name());
        productData.setPrice(productDto.price());
        productData.setDescription(productDto.description());

        this.productRepository.save(product);
        this.productDataRepository.save(productData);
        return product;
    }

    @Transactional
    public Product updateProduct(UUID id, ProductDto productDto) {
        Product product = this.productRepository.findById(id).orElse(null);
        if (product != null) {
            ProductData oldProductData = this.productDataRepository.findById(product.getProductDataId()).orElse(null);
            if(oldProductData != null) {
                oldProductData.setDeletedAt(LocalDateTime.now());
                this.productDataRepository.save(oldProductData);
            }

            ProductData productData = new ProductData();
            UUID productDataId = UUID.randomUUID();
            productData.setId(productDataId);
            productData.setName(productDto.name());
            productData.setPrice(productDto.price());
            productData.setDescription(productDto.description());
            productData.setProduct(product);

            product.setProductDataId(productDataId);
            this.productRepository.save(product);
            this.productDataRepository.save(productData);
            return product;
        }
        return null;
    }

    public void deleteProduct(UUID id) {
        ProductData productData = this.productDataRepository.findByProduct_IdAndDeletedAtIsNull(id).orElse(null);
        if (productData != null) {
            productData.setDeletedAt(LocalDateTime.now());
            this.productDataRepository.save(productData);
        }
    }
}
