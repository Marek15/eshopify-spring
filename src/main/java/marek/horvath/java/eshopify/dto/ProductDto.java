package marek.horvath.java.eshopify.dto;

import java.math.BigDecimal;

public record ProductDto(String name, BigDecimal price, String description) {
}
