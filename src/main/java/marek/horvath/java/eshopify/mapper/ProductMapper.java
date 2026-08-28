package marek.horvath.java.eshopify.mapper;

import marek.horvath.java.eshopify.dto.ProductResponseDto;
import marek.horvath.java.eshopify.entity.Product;
import marek.horvath.java.eshopify.entity.ProductData;

public class ProductMapper {

    public static ProductResponseDto toResponseDto(ProductData productData, Product product) {
        if (productData == null) {
            return null;
        }

        return new ProductResponseDto(
            product.getId(),
            productData.getName(),
            productData.getPrice(),
            productData.getDescription(),
            productData.getCreatedAt()
        );
    }

    public static ProductResponseDto toResponseDto(ProductData productData) {
        if (productData == null) {
            return null;
        }

        return new ProductResponseDto(
            productData.getProduct().getId(),
            productData.getName(),
            productData.getPrice(),
            productData.getDescription(),
            productData.getCreatedAt()
        );
    }
}
