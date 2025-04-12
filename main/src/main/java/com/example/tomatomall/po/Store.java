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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo")
    private String logoUrl;

    @Column(name = "owner_id")  // 添加店铺所有者ID字段
    private Integer ownerId;

    public StoreVO toVO(){
        StoreVO storeVO = new StoreVO();
        storeVO.setId(this.id);
        storeVO.setName(this.name);
        storeVO.setLogoUrl(this.logoUrl);
        return storeVO;
    }
}
