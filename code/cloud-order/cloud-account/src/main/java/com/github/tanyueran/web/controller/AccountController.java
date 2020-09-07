package com.github.tanyueran.web.controller;

import com.github.tanyueran.modal.CloudUser;
import com.github.tanyueran.service.AccountService;
import com.github.tanyueran.web.vo.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@ApiOperation("账号相关")
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public String login(@RequestBody User user) throws Exception {
        CloudUser u = new CloudUser();
        u.setUserCode(user.getUserCode());
        u.setPassword(user.getPassword());
        String token = accountService.login(u);
        return token;
    }

    // 请求用户信息
    @GetMapping("/info/{userCode}")
    public CloudUser getUserInfo(@PathVariable("userCode") String userCode) {
        CloudUser cloudUser = new CloudUser();
        cloudUser.setUserCode(userCode);
        CloudUser user = accountService.getUser(cloudUser);
        user.setPassword(null);
        return user;
    }

    // 注册
    @PostMapping("/register")
    public Boolean register(@RequestBody CloudUser cloudUser) throws Exception {
        return accountService.register(cloudUser);
    }

    @GetMapping("/a")
    @PreAuthorize("hasRole('MANAGER')")
    public String A() {
        return "a";
    }

    @GetMapping("/b")
    @PreAuthorize("hasRole('USER')")
    public String B() {
        return "b";
    }

}