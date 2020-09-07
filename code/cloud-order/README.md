# 外卖点菜-微服务

# 技术
1. spring cloud
2. spring security
3. swagger-ui
4. mysql
5. redis
6. spring cloud config
7. spring cloud gateway

## 微服务权限认证设计
```text
使用spring security 和 redis 、 jwt 作为技术基础。
认证服务登录成功后，将用户信息存入redis中（24小时有效），jwt私钥加密；
其他服务公钥解密成功后，直接从redis里面取出用户数据直接使用


```

## 问题记录
1. 多个微服务的swagger-ui 集成到一起
2. spring security 具体的权限校验
3. 权限角色中，使用网关来做，有权限就放，没有就拦截