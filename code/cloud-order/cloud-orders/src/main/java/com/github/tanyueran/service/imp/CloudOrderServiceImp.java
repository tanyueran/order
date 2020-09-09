package com.github.tanyueran.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanyueran.mapper.CloudOrderMapper;
import com.github.tanyueran.modal.dao.CloudOrder;
import com.github.tanyueran.service.CloudOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author tanxin
 * @since 2020-09-08
 */
@Service
public class CloudOrderServiceImp extends ServiceImpl<CloudOrderMapper, CloudOrder> implements CloudOrderService {

}
