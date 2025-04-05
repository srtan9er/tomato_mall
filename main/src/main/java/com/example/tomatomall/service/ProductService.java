package com.example.tomatomall.service;

import com.example.tomatomall.vo.ProductVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Boolean createProduct(ProductVO productVO);

    public List<ProductVO> getProductsByStoreId(Integer storeId);
}
