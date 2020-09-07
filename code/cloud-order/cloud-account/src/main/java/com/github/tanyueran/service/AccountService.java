package com.github.tanyueran.service;

import com.github.tanyueran.modal.CloudUser;

public interface AccountService {

    /**
     * 登录
     *
     * @param cloudUser
     * @return token
     */
    String login(CloudUser cloudUser) throws Exception;


    /**
     * 根据用户查询用户详情
     *
     * @param cloudUser
     * @return CloudUser
     */
    CloudUser getUser(CloudUser cloudUser);

    /**
     * 注册用户
     *
     * @param cloudUser
     * @return Boolean 是否注册成功
     */
    Boolean register(CloudUser cloudUser) throws Exception;

    /**
     * 检车账号是否可以使用
     *
     * @param userCode
     * @return Boolean 账号是否可以使用
     */
    Boolean checkUserCodeCanDo(String userCode) throws Exception;


}
