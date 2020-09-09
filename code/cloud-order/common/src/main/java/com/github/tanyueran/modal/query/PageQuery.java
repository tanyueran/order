package com.github.tanyueran.modal.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "分页查询PageQuery", description = "分页查询的基础实体")
@Data
public class PageQuery {

    @ApiModelProperty("页容量")
    private Integer size;

    @ApiModelProperty("查询的页号")
    private Integer current;
}
