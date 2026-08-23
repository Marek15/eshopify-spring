package marek.horvath.java.eshopify.dto;

import java.math.BigDecimal;

public record CreateProductDto(String name, BigDecimal price, String description) {
}
