package com.example.tomatomall.controller;

import com.example.tomatomall.service.ProductService;
import com.example.tomatomall.vo.ProductVO;
import com.example.tomatomall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResultVO<Boolean> createProduct(@RequestBody ProductVO productVO){
        return ResultVO.buildSuccess(productService.createProduct(productVO));
    }

    @GetMapping("/all/{id}")
    public ResultVO<List<ProductVO>> getAllProducts(@PathVariable("id") Integer id){
        return ResultVO.buildSuccess(productService.getProductsByStoreId(id));
    }
}
