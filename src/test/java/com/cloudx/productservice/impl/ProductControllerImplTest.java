package com.cloudx.productservice.impl;

import com.cloudx.productservice.controller.impl.ProductControllerImpl;
import com.cloudx.productservice.model.bo.ProductDTO;
import com.cloudx.productservice.model.request.ProductRequest;
import com.cloudx.productservice.model.response.ProductResponse;
import com.cloudx.productservice.service.Resilience4jService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerImplTest {

    @InjectMocks
    private ProductControllerImpl productController;

    @Mock
    private Resilience4jService resilience4JService;

    @Test
    void testGetProductRequestAt10AMOnDay14() {
        // Create test data
        Map<String, Object> httpHeadersMap = Map.of("service-id","price-service");
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = 1L;
        Long brandId = 2L;

        // Mock the behavior of productService and resilience4JService
        ProductRequest expectedRequest = ProductRequest.builder()
                .applicationDate(applicationDate)
                .productId(productId)
                .brandId(brandId)
                .build();
        ProductResponse expectedResponse = ProductResponse.builder()
                .product(ProductDTO.builder()
                        .productId(35455L)
                        .brandId(1L)
                        .priceList(1L)
                        .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                        .endDate(LocalDateTime.of(2020, 12, 31, 0, 0, 0))
                        .price(35.5)
                        .build())
                .resultCode("0")
                .resultMessage("OK")
                .build();

        when(resilience4JService.executeProduct(any())).thenReturn(ProductDTO.builder()
                .productId(35455L)
                .brandId(1L)
                .priceList(1L)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 0, 0, 0))
                .price(35.5)
                .build());

        var responseEntity = productController.getProduct(httpHeadersMap, expectedRequest.getApplicationDate()
                , expectedRequest.getProductId(), expectedRequest.getBrandId());

        // Assert the response
        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedResponse, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(200, responseEntity.getStatusCode().value());
        assertEquals(expectedResponse.getProduct().getProductId(),
                responseEntity.getBody().getProduct().getProductId());
        assertEquals(expectedResponse.getProduct().getBrandId(),
                responseEntity.getBody().getProduct().getBrandId());
        assertEquals(expectedResponse.getProduct().getPriceList(),
                responseEntity.getBody().getProduct().getPriceList());
        assertEquals(expectedResponse.getProduct().getStartDate(),
                responseEntity.getBody().getProduct().getStartDate());
        assertEquals(expectedResponse.getProduct().getEndDate(),
                responseEntity.getBody().getProduct().getEndDate());
        assertEquals(expectedResponse.getProduct().getPrice(),
                responseEntity.getBody().getProduct().getPrice());
    }
}