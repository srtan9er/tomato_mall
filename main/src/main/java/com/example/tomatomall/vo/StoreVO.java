package com.example.tomatomall.vo;

import com.example.tomatomall.po.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class StoreVO {

    private Integer id;

    private String name;

    private String logoUrl;

    public Store toPO(){
        Store store = new Store();
        store.setId(this.id);
        store.setName(this.name);
        store.setLogoUrl(this.logoUrl);
        return store;
    }
}
