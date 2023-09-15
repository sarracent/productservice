package com.cloudx.productservice.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Object containing PriceRequest")
public class ProductRequest {
    @Valid
    @NotNull(message = "Error el campo applicationDate es requerido.")
    private LocalDateTime applicationDate;
    @Valid
    @NotNull(message = "Error el campo productId es requerido.")
    private Long productId;
    @Valid
    @NotNull(message = "Error el campo brandId es requerido.")
    private Long brandId;
}
