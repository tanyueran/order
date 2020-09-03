package com.github.tanyueran.web.controller;

import com.github.tanyueran.modal.CloudUser;
import com.github.tanyueran.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@ApiOperation("账号相关")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public String login(@RequestBody CloudUser user) throws Exception {
        if (StringUtils.isEmpty(user.getUserCode()) || StringUtils.isEmpty(user.getPassword())) {
            throw new Exception("账号密码不可为空！");
        }
        CloudUser u = new CloudUser();
        u.setUserCode(user.getUserCode());
        u.setPassword(user.getPassword());
        String token = accountService.login(u);
        return token;
    }

    @GetMapping("/info/{userCode}")
    public CloudUser getUserInfo(@PathVariable("userCode") String userCode) {
        CloudUser cloudUser = new CloudUser();
        cloudUser.setUserCode(userCode);
        CloudUser user = accountService.getUser(cloudUser);
        user.setPassword(null);
        return user;
    }

    @GetMapping("/a")
    @RolesAllowed("MANAGER")
    public String A() {
        return "a";
    }

    @GetMapping("/b")
    @RolesAllowed("USER")
    public String B() {
        return "b";
    }
}
