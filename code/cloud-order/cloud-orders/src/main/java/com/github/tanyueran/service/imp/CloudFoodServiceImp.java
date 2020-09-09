package com.github.tanyueran.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanyueran.mapper.CloudFoodMapper;
import com.github.tanyueran.modal.dao.CloudFood;
import com.github.tanyueran.service.CloudFoodService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品表 服务实现类
 * </p>
 *
 * @author tanxin
 * @since 2020-09-08
 */
@Service
public class CloudFoodServiceImp extends ServiceImpl<CloudFoodMapper, CloudFood> implements CloudFoodService {

}
