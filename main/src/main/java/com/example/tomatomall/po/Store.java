package com.example.tomatomall.po;

import com.example.tomatomall.vo.StoreVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Store {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "logo")
    private String logoUrl;

    public StoreVO toVO(){
        StoreVO storeVO = new StoreVO();
        storeVO.setId(this.id);
        storeVO.setName(this.name);
        storeVO.setLogoUrl(this.logoUrl);
        return storeVO;
    }
}
