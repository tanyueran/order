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
}
