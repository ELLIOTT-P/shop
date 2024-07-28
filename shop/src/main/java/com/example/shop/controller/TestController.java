package com.example.shop.controller;

import com.example.shop.entity.Result;
import com.example.shop.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
@Validated
public class TestController {
    //测试

    @Autowired
    private TestService testService;

    @GetMapping("list")
    public Result getList(){
        Object list = testService.getList();
        return Result.succ(list);
    }

}
