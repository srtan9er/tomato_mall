package com.example.tomatomall.vo;

import com.example.tomatomall.po.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ProductVO {

    private Integer id;

    private String name;

    private Integer price;

    private Integer storeId;

    public Product toPO(){
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setPrice(this.price);
        product.setStoreId(this.storeId);
        return product;
    }
}
