package com.cloudx.productservice.service.impl;

import com.cloudx.productservice.annotations.log.LogService;
import com.cloudx.productservice.model.Product;
import com.cloudx.productservice.model.bo.ProductDTO;
import com.cloudx.productservice.model.request.ProductRequest;
import com.cloudx.productservice.repository.ProductRepository;
import com.cloudx.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @LogService
    @Override
    public List<ProductDTO> findProductsByParameters(ProductRequest request) {
        List<Product> productList = productRepository.findProductInfo(request.getApplicationDate(), request.getProductId(), request.getBrandId());
        return mapToProductDTO(productList);
    }

    private List<ProductDTO> mapToProductDTO(List<Product> productList) {
        return productList.stream()
                .map(this::mapRowToProductDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO mapRowToProductDTO(Product row) {
        return ProductDTO.builder()
                .productId(row.getProductId())
                .brandId(row.getBrandId())
                .priceList(row.getPriceList())
                .startDate(row.getStartDate())
                .endDate(row.getEndDate())
                .price(row.getPrice())
                .build();
    }
}
