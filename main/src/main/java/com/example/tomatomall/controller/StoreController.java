package com.example.tomatomall.controller;

import com.example.tomatomall.service.StoreService;
import com.example.tomatomall.vo.ResultVO;
import com.example.tomatomall.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/create")
    public ResultVO<Boolean> createStore(@RequestBody StoreVO storeVO){
        return ResultVO.buildSuccess(storeService.createStore(storeVO));
    }

    @GetMapping("/{id}")
    public ResultVO<StoreVO> searchShops(@RequestParam Integer id){
        return ResultVO.buildSuccess(storeService.searchStoreById(id));
    }

    @GetMapping("/all")
    public ResultVO<List<StoreVO>> getAllShops(){
        return ResultVO.buildSuccess(storeService.getAllStores());
    }

}
