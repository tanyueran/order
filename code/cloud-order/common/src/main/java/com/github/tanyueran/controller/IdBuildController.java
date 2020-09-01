package com.github.tanyueran.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IdBuildController {

    @GetMapping("/id/{num}")
    public List<String> getIds(@PathVariable("num") Integer num) {
        return null;
    }

    @GetMapping("/test")
    public String test() {
        return "common";
    }
}
