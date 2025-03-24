package com.example.tomatomall.mapper;

import com.example.tomatomall.po.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    Account findByUsername(String username);
    void insert(Account account);
    void update(Account account);
    boolean existsByUsername(String username);
}