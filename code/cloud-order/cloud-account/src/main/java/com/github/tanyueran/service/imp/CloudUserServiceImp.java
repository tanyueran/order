package com.github.tanyueran.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanyueran.constant.RedisPre;
import com.github.tanyueran.mapper.CloudUserMapper;
import com.github.tanyueran.modal.dao.CloudUser;
import com.github.tanyueran.service.CloudUserService;
import com.github.tanyueran.utils.JwtUtil;
import com.github.tanyueran.model.query.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author tanxin
 * @since 2020-09-08
 */
@Service
public class CloudUserServiceImp extends ServiceImpl<CloudUserMapper, CloudUser> implements CloudUserService {

    @Autowired
    private PrivateKey privateKey;

    @Resource(name = "myRedisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public String login(User user) throws Exception {
        QueryWrapper<CloudUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user_code", user.getUserCode());
        CloudUser cloudUser = this.getOne(wrapper);
        if (cloudUser == null) {
            throw new Exception("账号密码错误！");
        }
        Map<String, String> map = new HashMap<>();
        map.put("userCode", cloudUser.getUserCode());
        redisTemplate.opsForValue().set(RedisPre.USER_REIDS_PRE + cloudUser.getUserCode(), cloudUser);
        redisTemplate.expire(RedisPre.USER_REIDS_PRE + cloudUser.getUserCode(), 24 * 60 * 60, TimeUnit.SECONDS);
        String token = JwtUtil.genToken(map, (RSAPrivateKey) privateKey, new Date(new Date().getTime() + 24 * 60 * 60 * 1000));
        return token;
    }
}
