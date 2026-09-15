package marek.horvath.java.eshopify.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDto(
    @NotBlank String name,
    @NotNull @DecimalMin(value = "0.00") @Digits(integer = 12, fraction = 2) BigDecimal price,
    String description
) {
}
