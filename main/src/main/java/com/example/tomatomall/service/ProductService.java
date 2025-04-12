package com.example.tomatomall.service;

import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.StockpileVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductVO> getAllProducts();
    ProductVO getProductById(Integer id);
    ProductVO createProduct(ProductVO productVO);
    void updateProduct(ProductVO productVO);
    String deleteProduct(Integer id);
    String adjustStockpile(Integer productId, Integer amount);
    StockpileVO getStockpile(Integer productId);
}
