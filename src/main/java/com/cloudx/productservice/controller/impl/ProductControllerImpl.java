package com.cloudx.productservice.controller.impl;

import com.cloudx.productservice.annotations.auditable.AuditableApi;
import com.cloudx.productservice.annotations.auditable.AuditableParamIgnore;
import com.cloudx.productservice.annotations.auditable.AuditableReturn;
import com.cloudx.productservice.controller.ProductController;
import com.cloudx.productservice.model.request.ProductRequest;
import com.cloudx.productservice.model.response.ProductResponse;
import com.cloudx.productservice.service.ProductService;
import com.cloudx.productservice.service.Resilience4jService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

import static com.cloudx.productservice.commons.resolver.CustomHeadersResolver.getHeadersMap;
import static com.cloudx.productservice.commons.resolver.CustomHeadersResolver.validateHeaders;
import static com.cloudx.productservice.constants.Constants.OK_MSG;
import static com.cloudx.productservice.constants.Constants.ZERO_MSG;

@RestController
@AllArgsConstructor
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;
    private final Resilience4jService resilience4JService;

    @Override
    @AuditableApi(
            description = "getCellularByCellularNumber Api",
            parameterIgnore = @AuditableParamIgnore(nameToAudit = "httpHeadersMap", type = Map.class),
            returnMethod = @AuditableReturn(type = ProductResponse.class))
    public ResponseEntity<ProductResponse> getProduct(final Map<String, Object> httpHeadersMap,
                                                     final LocalDateTime applicationDate,
                                                     final Long productId,
                                                     final Long brandId) {

        final Map<String, String> headersMap = getHeadersMap(httpHeadersMap);
        validateHeaders(headersMap);

        var request = ProductRequest.builder()
                .applicationDate(applicationDate)
                .productId(productId)
                .brandId(brandId).build();
        var response = ProductResponse.builder()
                .product(resilience4JService.executeProduct(() -> productService.findProductsByParameters(request)))
                .resultCode(ZERO_MSG)
                .resultMessage(OK_MSG)
                .build();

        return ResponseEntity.ok()
                .body(response);
    }
}
