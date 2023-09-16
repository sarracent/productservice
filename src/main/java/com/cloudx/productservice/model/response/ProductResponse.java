package com.cloudx.productservice.model.response;

import com.cloudx.productservice.model.bo.ProductDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Object containing PriceResponse")
public class ProductResponse extends ServiceResponse {
    @Schema(description = "Product")
    private ProductDTO product;
}
