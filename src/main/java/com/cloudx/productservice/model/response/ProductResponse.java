package com.cloudx.productservice.model.response;

import com.cloudx.productservice.model.bo.ProductDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Object containing PriceResponse")
public class ProductResponse extends ServiceResponse {
    @Schema(description = "List of Products")
    private List<ProductDTO> product;
}
