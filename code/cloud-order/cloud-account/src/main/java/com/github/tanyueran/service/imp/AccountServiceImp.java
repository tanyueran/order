package com.github.tanyueran.service.imp;

import com.alibaba.druid.util.StringUtils;
import com.github.tanyueran.constant.RedisPre;
import com.github.tanyueran.mapper.CloudUserMapper;
import com.github.tanyueran.modal.CloudUser;
import com.github.tanyueran.modal.CloudUserExample;
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
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private CloudUserMapper cloudUserMapper;

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
        redisTemplate.opsForValue().set(RedisPre.USER_REIDS_PRE + user.getUserCode(), user);
        redisTemplate.expire(user.getUserCode(), timeout, TimeUnit.SECONDS);
        HashMap<String, String> map = new HashMap<>();
        map.put("userCode", user.getUserCode());
        String token = JwtUtil.genToken(map, (RSAPrivateKey) privateKey, new Date(new Date().getTime() + timeout * 1000));
        return token;
    }

    @Override
    public CloudUser getUser(CloudUser cloudUser) {
        return cloudUserMapper.selectOne(cloudUser);
    }

    @Override
    public Boolean register(CloudUser cloudUser) throws Exception {
        CloudUserExample example = new CloudUserExample();
        example.createCriteria().andUserCodeEqualTo(cloudUser.getUserCode());
        // 判断用户名是否被使用
        Boolean aBoolean = checkUserCodeCanDo(cloudUser.getUserCode());
        if (!aBoolean) {
            throw new Exception("账号已被使用");
        }
        int insert = cloudUserMapper.insert(cloudUser);
        return insert == 1;
    }

    @Override
    public Boolean checkUserCodeCanDo(String userCode) throws Exception {
        if (StringUtils.isEmpty(userCode)) {
            throw new Exception("userCode参数不可为空");
        }
        CloudUserExample userExample = new CloudUserExample();
        userExample.createCriteria().andUserCodeEqualTo(userCode);
        List<CloudUser> users = cloudUserMapper.selectByExample(userExample);
        return users.size() < 1;
    }
}
