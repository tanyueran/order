package com.github.tanyueran.web.controller;

import com.github.tanyueran.utils.IdBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IdBuildController {

    private static IdBuilder idBuilder = new IdBuilder(1, 1, 1);

    @GetMapping("/id/{num}")
    public List<String> getIds(@PathVariable("num") Integer num) throws Exception {
        if (num <= 0) {
            throw new Exception("参数异常");
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(idBuilder.nextId() + "");
        }
        return list;
    }

}
