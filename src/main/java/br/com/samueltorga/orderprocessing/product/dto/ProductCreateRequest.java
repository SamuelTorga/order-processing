package br.com.samueltorga.orderprocessing.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductCreateRequest(@NotBlank String name,
                                   @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal defaultPrice) {
}
