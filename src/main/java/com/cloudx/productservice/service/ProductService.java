package com.cloudx.productservice.service;

import com.cloudx.productservice.model.bo.ProductDTO;
import com.cloudx.productservice.model.request.ProductRequest;


public interface ProductService {
    ProductDTO findProductByParameters(ProductRequest request);
}
