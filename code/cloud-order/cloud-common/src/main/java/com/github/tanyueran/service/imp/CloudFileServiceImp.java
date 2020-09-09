package com.github.tanyueran.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tanyueran.mapper.CloudFileMapper;
import com.github.tanyueran.modal.dao.CloudFile;
import com.github.tanyueran.service.CloudFileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author tanxin
 * @since 2020-09-08
 */
@Service
public class CloudFileServiceImp extends ServiceImpl<CloudFileMapper, CloudFile> implements CloudFileService {

}
