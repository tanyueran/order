package com.github.tanyueran.service.imp;

import com.github.tanyueran.mapper.CloudUserMapper;
import com.github.tanyueran.modal.CloudUser;
import com.github.tanyueran.service.AccountService;
import com.github.tanyueran.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private CloudUserMapper userMapper;

    @Autowired
    private PrivateKey privateKey;

    @Resource(name = "myRedisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public String login(CloudUser cloudUser) throws Exception {
        CloudUser user = this.getUser(cloudUser);
        // 超时时间
        Integer timeout = 24 * 60 * 60;
        user.setPassword(null);
        redisTemplate.opsForValue().set(user.getUserCode(), user);
        redisTemplate.expire(user.getUserCode(), timeout, TimeUnit.SECONDS);
        HashMap<String, String> map = new HashMap<>();
        map.put("userCode", user.getUserCode());
        String token = JwtUtil.genToken(map, (RSAPrivateKey) privateKey, new Date(new Date().getTime() + timeout * 1000));
        return token;
    }

    @Override
    public CloudUser getUser(CloudUser cloudUser) {
        return userMapper.selectOne(cloudUser);
    }
}
