package com.github.tanyueran.service;

import java.util.List;

public interface IdBuilderService {
    /**
     * 主键生成
     *
     * @param number
     * @return
     */
    List<String> buildIds(Integer number) throws Exception;
}
