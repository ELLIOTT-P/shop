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

    /**
     * 获取商品列表
     * @param tabType
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("getMaterialList")
    public Result getMaterialList(
            @RequestParam(required = false, defaultValue = "1") Integer tabType,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "1") Integer dr
    ){
        List<Map<String, Object>> materialList = materialService.getMaterialList(tabType, pageNum, pageSize,dr);
        return Result.succ(materialList);
    }

    /**
     * 获取商品详情
     * @param spuId
     * @return
     */
    @GetMapping("getMaterialById")
    public Result getMaterialById(@RequestParam(required = true, defaultValue = "1") Integer spuId){
        Map<String, Object> map = materialService.getMaterialById(spuId);
        return Result.succ(map);
    }

    /**
     * 获取首页信息
     * @return
     */
    @GetMapping("getHome")
    public Result getHome(){
        Map<String, Object> home = materialService.getHome();
        return Result.succ(home);
    }
}
