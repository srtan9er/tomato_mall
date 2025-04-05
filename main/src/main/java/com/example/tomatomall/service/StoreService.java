package com.example.tomatomall.service;

import com.example.tomatomall.vo.StoreVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StoreService {
    public Boolean createStore(StoreVO storeVO);

    public List<StoreVO> getAllStores();

    public StoreVO searchStoreById(Integer id);
}
