package com.example.tomatomall.servicelmpl;

import com.example.tomatomall.exception.TomatoException;
import com.example.tomatomall.po.Store;
import com.example.tomatomall.repository.StoreRepository;
import com.example.tomatomall.service.StoreService;
import com.example.tomatomall.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServicelmpl implements StoreService {
    @Autowired
    StoreRepository storeRepository;

    @Override
    public Boolean createStore(StoreVO storeVO) {
        Store store = storeRepository.findByName(storeVO.getName());
        if(store != null){
            throw TomatoException.nameAlreadyExists();
        }
        Store newStore = storeVO.toPO();
        storeRepository.save(newStore);
        return true;
    }

    @Override
    public StoreVO searchStoreById(Integer id){
        StoreVO storeVO = new StoreVO();
        storeVO.setId(1);
        storeVO.setName("TestStore");
        storeVO.setLogoUrl("logoUrl");// which url?
        return storeVO;
    }

    @Override
    public List<StoreVO> getAllStores(){
        return storeRepository.findAll().stream().map(Store::toVO).collect(Collectors.toList());
    }
}
