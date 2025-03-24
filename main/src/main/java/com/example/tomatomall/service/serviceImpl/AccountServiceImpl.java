package com.example.tomatomall.service.serviceImpl;

import com.example.tomatomall.mapper.AccountMapper;
import com.example.tomatomall.po.Account;
import com.example.tomatomall.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Account getUser(String username) {
        Account account = accountMapper.findByUsername(username);
        if (account != null) {
            account.setPassword(null); // 不返回密码
        }
        return account;
    }

    @Override
    public Account createUser(Account account) {
        if (accountMapper.existsByUsername(account.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountMapper.insert(account);
        account.setPassword(null);
        return account;
    }

    @Override
    public Account updateUser(Account account) {
        Account existingAccount = accountMapper.findByUsername(account.getUsername());
        if (existingAccount == null) {
            throw new RuntimeException("用户不存在");
        }
        accountMapper.update(account);
        return getUser(account.getUsername());
    }

    @Override
    public String login(String username, String password) {
        Account account = accountMapper.findByUsername(username);
        if (account == null || !passwordEncoder.matches(password, account.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        return UUID.randomUUID().toString(); // 生成简单的token
    }
}
