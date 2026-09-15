package marek.horvath.java.eshopify.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDto(
    UUID id,
    String name,
    BigDecimal price,
    String description,
    LocalDateTime createdAt
) {
}
