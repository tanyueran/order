package com.github.tanyueran.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.tanyueran.modal.dao.CloudUser;
import com.github.tanyueran.model.query.User;

/**
 * <p>
 * 账户表 服务类
 * </p>
 *
 * @author tanxin
 * @since 2020-09-08
 */
public interface CloudUserService extends IService<CloudUser> {
    /**
     * 登录
     *
     * @return
     */
    String login(User user) throws Exception;
}
