package com.github.tanyueran.modal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "分页查询结果实体", description = "分页查询结果实体")
@Data
public class PageQueryResult {

    @ApiModelProperty("总页数")
    private Integer total;

    @ApiModelProperty("当前页")
    private Integer current;

    @ApiModelProperty("页容量")
    private Integer size;

    @ApiModelProperty("分页数据")
    private Object data;

}
