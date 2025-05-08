package br.com.samueltorga.orderprocessing.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(Integer id, String name, BigDecimal defaultPrice, LocalDateTime created, boolean active) {
}
