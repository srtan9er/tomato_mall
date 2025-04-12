package com.example.tomatomall.controller;

import com.example.tomatomall.service.ProductService;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.ResultVO;
import com.example.tomatomall.vo.StockpileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResultVO<List<ProductVO>> getAllProducts() {
        return ResultVO.buildSuccess(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResultVO<ProductVO> getProduct(@PathVariable Integer id) {
        return ResultVO.buildSuccess(productService.getProductById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResultVO<ProductVO> createProduct(@RequestBody @Valid ProductVO productVO) {
        return ResultVO.buildSuccess(productService.createProduct(productVO));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")  // 添加权限控制
    public ResultVO<Void> updateProduct(@RequestBody @Valid ProductVO productVO) {
        productService.updateProduct(productVO);
        return ResultVO.buildSuccess();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultVO<String> deleteProduct(@PathVariable Integer id) {
        return ResultVO.buildSuccess(productService.deleteProduct(id));
    }

    @PatchMapping("/stockpile/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResultVO<String> adjustStockpile(
            @PathVariable Integer productId,
            @RequestParam Integer amount) {
        return ResultVO.buildSuccess(productService.adjustStockpile(productId, amount));
    }

    @GetMapping("/stockpile/{productId}")
    public ResultVO<StockpileVO> getStockpile(@PathVariable Integer productId) {
        return ResultVO.buildSuccess(productService.getStockpile(productId));
    }
}
