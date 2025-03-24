package com.example.tomatomall.service;

import com.example.tomatomall.po.Account;

public interface AccountService {
    Account getUser(String username);
    Account createUser(Account account);
    Account updateUser(Account account);
    String login(String username, String password);
}
