package com.cloudx.productservice.service.impl;

import com.cloudx.productservice.exception.impl.TechnicalException;
import com.cloudx.productservice.model.Product;
import com.cloudx.productservice.model.bo.ProductDTO;
import com.cloudx.productservice.model.request.ProductRequest;
import com.cloudx.productservice.repository.ProductRepository;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    void testFindProductByParametersProductFound() {

        when(productRepository.findProductInfo(any(), any(), any())).thenReturn(List.of(Product.builder()
                .brandId(1L)
                .startDate(LocalDateTime.of(2020, 6, 14, 10, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .priceList(1L)
                .productId(35455L)
                .priority(0)
                .price(35.50)
                .curr("EUR")
                .build(),
                Product.builder()
                        .brandId(1L)
                        .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                        .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                        .priceList(3L)
                        .productId(35455L)
                        .priority(1)
                        .price(30.50)
                        .curr("EUR")
                        .build()));

        Product highPriorityProduct = Product.builder()
                .brandId(1L)
                .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
                .priceList(3L)
                .productId(35455L)
                .priority(1)
                .price(30.50)
                .curr("EUR")
                .build();

        ProductDTO result = productService.findProductByParameters(ProductRequest.builder()
                .applicationDate(LocalDateTime.of(2020, 6, 14, 10, 0, 0))
                .productId(1L)
                .brandId(2L)
                .build());

        assertEquals(highPriorityProduct.getProductId(), result.getProductId());
        assertEquals(highPriorityProduct.getBrandId(), result.getBrandId());
        assertEquals(highPriorityProduct.getPriceList(), result.getPriceList());
        assertEquals(highPriorityProduct.getStartDate(), result.getStartDate());
        assertEquals(highPriorityProduct.getEndDate(), result.getEndDate());
        assertEquals(highPriorityProduct.getPrice(), result.getPrice());
    }

    @Test
    void testFindProductByParametersProductNotFound() {
        when(productRepository.findProductInfo(any(), any(), any())).thenReturn(Collections.emptyList());


        TechnicalException thrown = assertThrows(TechnicalException.class, () -> productService.
                findProductByParameters(ProductRequest.builder()
                        .applicationDate(LocalDateTime.of(2020, 6, 14, 10, 0, 0))
                        .productId(1L)
                        .brandId(2L)
                        .build()));

        assertEquals("200101", thrown.getCode());
        assertEquals("Error no se encontraron registros en Product con el valor 1",
                thrown.getMessage());
    }
}