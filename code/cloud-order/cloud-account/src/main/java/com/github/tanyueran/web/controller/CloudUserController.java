package com.github.tanyueran.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.tanyueran.modal.dao.CloudUser;
import com.github.tanyueran.service.CloudUserService;
import com.github.tanyueran.model.query.User;
import com.github.tanyueran.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * <p>
 * 账户表 前端控制器
 * </p>
 *
 * @author tanxin
 * @since 2020-09-08
 */
@RestController
@Validated
@Api(value = "账户controller", tags = "账户相关")
public class CloudUserController {

    @Autowired
    private CloudUserService cloudUserService;

    @Autowired
    private PublicKey publicKey;

    @PostMapping("/login")
    @ApiOperation("登录")
    public String login(@RequestBody User user) throws Exception {
        return cloudUserService.login(user);
    }

    @GetMapping("/info")
    @ApiOperation("请求当前用户的详情信息")
    public CloudUser getSelfInfo(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        Map<String, String> map = JwtUtil.verifyToken(token, (RSAPublicKey) publicKey);
        String userCode = map.get("userCode");
        QueryWrapper<CloudUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_code", userCode);
        CloudUser cloudUser = cloudUserService.getOne(wrapper);
        return cloudUser;
    }

    // 请求用户信息
    @GetMapping("/info/{userCode}")
    @ApiOperation("请求用户的详细信息")
    @PreAuthorize("hasRole('MANAGER')")
    public CloudUser getUserInfo(@ApiParam(name = "userCode", value = "用户的账号") @PathVariable("userCode") String userCode) {
        QueryWrapper<CloudUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_code", userCode);
        CloudUser cloudUser = cloudUserService.getOne(wrapper);
        return cloudUser;
    }

    // 注册
    @PostMapping("/register")
    @ApiOperation("注册")
    public Boolean register(@RequestBody CloudUser cloudUser) throws Exception {
        boolean save = cloudUserService.save(cloudUser);
        return save;
    }

    // 人员分页查询

}
