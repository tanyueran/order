package com.github.tanyueran.web.controller;

import com.github.tanyueran.service.IdBuilderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "id生成器", tags = "id生成器")
public class IdBuildController {

    @Autowired
    private IdBuilderService idBuilderService;


    @GetMapping("/id/{num}")
    @ApiOperation("id生成")
    public List<String> getIds(@ApiParam(name = "num", value = "id个数") @PathVariable("num") Integer num) throws Exception {
        if (num <= 0) {
            throw new Exception("参数异常");
        }
        return idBuilderService.buildIds(num);
    }

}
