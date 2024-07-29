package com.example.shop.controller;

import com.example.shop.entity.Result;
import com.example.shop.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Validated
@RequestMapping("/miniProgram")
public class miniController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("getMaterialList")
    public Result getMaterialList(
            @RequestParam(required = false, defaultValue = "1") Integer tabType,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize
    ){
        List<Map<String, Object>> materialList = materialService.getMaterialList(tabType, pageNum, pageSize);
        return Result.succ(materialList);
    }

}
