package com.cloudx.productservice.service.impl;

import com.cloudx.productservice.annotations.log.LogService;
import com.cloudx.productservice.exception.impl.TechnicalException;
import com.cloudx.productservice.model.Product;
import com.cloudx.productservice.model.bo.ProductDTO;
import com.cloudx.productservice.model.request.ProductRequest;
import com.cloudx.productservice.repository.ProductRepository;
import com.cloudx.productservice.service.ProductService;
import com.cloudx.productservice.utility.Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.cloudx.productservice.constants.Errors.ERROR_DATABASE_PRODUCT_NOT_FOUND;
import static com.cloudx.productservice.constants.Errors.ERROR_PRODUCT_NOT_VALUE;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @LogService
    @Override
    public ProductDTO findProductByParameters(ProductRequest request) {
        final var productList = productRepository.findProductInfo(
                request.getApplicationDate(),
                request.getProductId(),
                request.getBrandId()
        );

        if (Util.isNullOrEmpty(productList)) {
            throw new TechnicalException(
                    ERROR_DATABASE_PRODUCT_NOT_FOUND.getCode(),
                    String.format(ERROR_DATABASE_PRODUCT_NOT_FOUND.getMessage(), request.getProductId())
            );
        }

        return getProductHighPriority(productList)
                .map((Product product) -> mapProductToProductDTO(Optional.of(product)))   // Asume que mapProductToProductDTO devuelve un Optional<ProductDTO>
                .orElseThrow(() -> new TechnicalException(
                        ERROR_PRODUCT_NOT_VALUE.getCode(),
                        String.format(ERROR_PRODUCT_NOT_VALUE.getMessage(), request.getProductId())
                ))
                .get();
    }

    private Optional<Product> getProductHighPriority(List<Product> productList) {
        return productList.stream()
                .max(Comparator.comparingInt(Product::getPriority));
    }


    private Optional<ProductDTO> mapProductToProductDTO(Optional<Product> product) {
        return product.map(p -> ProductDTO.builder()
                .productId(p.getProductId())
                .brandId(p.getBrandId())
                .priceList(p.getPriceList())
                .startDate(p.getStartDate())
                .endDate(p.getEndDate())
                .price(p.getPrice())
                .build());
    }
}
