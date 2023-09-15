package com.cloudx.productservice.service;

import com.cloudx.productservice.model.bo.ProductDTO;
import com.cloudx.productservice.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findProductsByParameters(ProductRequest request);
}
