package com.github.tanyueran.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "user实体", description = "用户登录的实体")
@Data
public class User {

    @NotBlank(message = "账号不可为空")
    @ApiModelProperty(value = "账号", name = "userCode", required = true)
    private String userCode;

    @NotBlank(message = "密码不可为空")
    @ApiModelProperty(value = "密码", name = "password", required = true)
    private String password;

    @ApiModelProperty(value = "验证码", name = "password")
    private String authCode;
}
