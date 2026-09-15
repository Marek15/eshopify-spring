package marek.horvath.java.eshopify.services;

import jakarta.transaction.Transactional;
import marek.horvath.java.eshopify.entity.Product;
import marek.horvath.java.eshopify.dto.ProductDto;
import marek.horvath.java.eshopify.dto.ProductResponseDto;
import marek.horvath.java.eshopify.entity.ProductData;
import marek.horvath.java.eshopify.mapper.ProductMapper;
import marek.horvath.java.eshopify.repository.ProductDataRepository;
import marek.horvath.java.eshopify.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDataRepository productDataRepository;

    public ProductService(ProductRepository productRepository, ProductDataRepository productDataRepository) {
        this.productRepository = productRepository;
        this.productDataRepository = productDataRepository;
    }

    public List<ProductResponseDto> getAllProducts() {
        return this.productDataRepository.findAllActive().stream()
            .map(ProductMapper::toResponseDto)
            .collect(Collectors.toList());
    }

    public ProductResponseDto getProductById(UUID id) {
        ProductData productData = this.productDataRepository.findByProduct_IdAndDeletedAtIsNull(id).orElse(null);
        return ProductMapper.toResponseDto(productData);
    }

    @Transactional
    public Product createProduct(ProductDto productDto) {
        Product product = new Product();
        product = this.productRepository.save(product);

        ProductData productData = new ProductData();
        productData.setProduct(product);
        product.setProductDataId(productData.getId());

        productData.setName(productDto.name());
        productData.setPrice(productDto.price());
        productData.setDescription(productDto.description());

        this.productDataRepository.save(productData);

        product.setProductDataId(productData.getId());
        this.productRepository.save(product);

        return product;
    }

    @Transactional
    public Product updateProduct(UUID id, ProductDto productDto) {
        Product product = this.productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }

ProductData oldProductData = this.productDataRepository.findByProduct_IdAndDeletedAtIsNull(id).orElse(null);
        if (oldProductData == null) {
            return null;
        }

        oldProductData.setDeletedAt(LocalDateTime.now());
        this.productDataRepository.save(oldProductData);

        ProductData productData = new ProductData();
        productData.setProduct(product);
        productData.setName(productDto.name() != null ? productDto.name() : oldProductData.getName());
        productData.setPrice(productDto.price() != null ? productDto.price() : oldProductData.getPrice());
        productData.setDescription(productDto.description() != null ? productDto.description() : oldProductData.getDescription());

        this.productDataRepository.save(productData);

        product.setProductDataId(productData.getId());
        this.productRepository.save(product);
        return product;
    }

    public void deleteProduct(UUID id) {
        ProductData productData = this.productDataRepository.findByProduct_IdAndDeletedAtIsNull(id).orElse(null);
        if (productData != null) {
            productData.setDeletedAt(LocalDateTime.now());
            this.productDataRepository.save(productData);
        }
    }
}
