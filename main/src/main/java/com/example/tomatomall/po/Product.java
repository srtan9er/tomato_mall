package com.example.tomatomall.po;

import com.example.tomatomall.vo.ProductVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Double rate;

    @Column(length = 255)
    private String description;

    @Column(length = 500)
    private String cover;

    @Column(length = 500)
    private String detail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Specification> specifications;

    // 添加与 Store 的关联关系
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Stockpile stockpile;

    public ProductVO toVO() {
        ProductVO vo = new ProductVO();
        vo.setId(this.id);
        vo.setTitle(this.title);
        vo.setPrice(this.price);
        vo.setRate(this.rate);
        vo.setDescription(this.description);
        vo.setCover(this.cover);
        vo.setDetail(this.detail);
        vo.setSpecifications(this.specifications);
        return vo;
    }
}
