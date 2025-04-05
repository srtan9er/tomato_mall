package com.example.tomatomall.servicelmpl;

import com.example.tomatomall.po.Product;
import com.example.tomatomall.repository.ProductRepository;
import com.example.tomatomall.service.ProductService;
import com.example.tomatomall.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServicelmpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Boolean createProduct(ProductVO productVO){
        Product newProduct = productVO.toPO();
        productRepository.save(newProduct);
        return true;
    }

    private ProductVO convertToVO(Product product){
        ProductVO vo = new ProductVO();
        vo.setId(product.getId());
        vo.setName(product.getName());
        vo.setPrice(product.getPrice());
        vo.setStoreId(product.getStoreId());
        return vo;
    }

    private List<ProductVO> convertToVOList(List<Product> products) {
        return products.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductVO> getProductsByStoreId(Integer storeId){
        List<Product> products = productRepository.findByStoreId(storeId);
        return convertToVOList(products);
    }
}
