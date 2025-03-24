package com.example.tomatomall.controller;

import com.example.tomatomall.po.Account;
import com.example.tomatomall.service.AccountService;
import com.example.tomatomall.service.TokenService;
import com.example.tomatomall.vo.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Resource
    AccountService accountService;

    @Resource
    private TokenService tokenService;

    @GetMapping("/{username}")
    public Response getUser(@PathVariable String username, HttpServletRequest request) {
        try {
            String currentUser = (String) request.getAttribute("currentUser");
            if (!username.equals(currentUser)) {
                return Response.buildFailure("无权访问", "403");
            }
            return Response.buildSuccess(accountService.getUser(username));
        } catch (Exception e) {
            return Response.buildFailure(e.getMessage(), "400");
        }
    }

    @PostMapping
    public Response createUser(@Valid @RequestBody Account account) {
        try {
            return Response.buildSuccess(accountService.createUser(account));
        } catch (Exception e) {
            return Response.buildFailure(e.getMessage(), "400");
        }
    }

    @PutMapping
    public Response updateUser(@Valid @RequestBody Account account, HttpServletRequest request) {
        try {
            String currentUser = (String) request.getAttribute("currentUser");
            if (!account.getUsername().equals(currentUser)) {
                return Response.buildFailure("无权修改他人信息", "403");
            }
            return Response.buildSuccess(accountService.updateUser(account));
        } catch (Exception e) {
            return Response.buildFailure(e.getMessage(), "400");
        }
    }

    @PostMapping("/login")
    public Response login(@Valid @RequestBody Account account, HttpServletResponse response) {
        try {
            String token = accountService.login(account.getUsername(), account.getPassword());
            token = tokenService.generateToken(account.getUsername());
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return Response.buildSuccess(token);
        } catch (Exception e) {
            return Response.buildFailure(e.getMessage(), "400");
        }
    }
}
