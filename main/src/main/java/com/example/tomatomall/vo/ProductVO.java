package com.example.tomatomall.vo;

import com.example.tomatomall.po.Product;
import com.example.tomatomall.po.Specification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductVO {
    private Integer id;
    private String title;
    private BigDecimal price;
    private Double rate;
    private String description;
    private String cover;
    private String detail;
    private Set<Specification> specifications;
    private Integer storeId;  // 添加店铺ID字段
    public Product toPO() {
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setPrice(this.price);
        product.setRate(this.rate);
        product.setDescription(this.description);
        product.setCover(this.cover);
        product.setDetail(this.detail);
        product.setSpecifications(this.specifications);
        // 不需要在这里设置 store，因为在 service 层会处理
        return product;
    }
}
