package com.example.tomatomall.repository;

import com.example.tomatomall.po.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    Store findByName(String name);
}
