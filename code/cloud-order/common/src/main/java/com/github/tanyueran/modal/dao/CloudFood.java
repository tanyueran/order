package com.github.tanyueran.modal.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜品表
 * </p>
 *
 * @author tanxin
 * @since 2020-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tab_food")
@ApiModel(value="CloudFood对象", description="菜品表")
public class CloudFood implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "菜品名称")
    @TableField("food_name")
    private String foodName;

    @ApiModelProperty(value = "菜品类型")
    @TableField("food_type")
    private Integer foodType;

    @ApiModelProperty(value = "菜品介绍")
    @TableField("food_detail")
    private String foodDetail;

    @ApiModelProperty(value = "价格")
    @TableField("price")
    private Double price;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "菜品头像id")
    @TableField("img_id")
    private String imgId;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;


}
