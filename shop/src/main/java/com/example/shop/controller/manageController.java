package com.example.shop.controller;

import com.example.shop.entity.ManageUser;
import com.example.shop.entity.Material;
import com.example.shop.entity.Result;
import com.example.shop.service.ManageService;
import com.example.shop.service.MaterialService;
import com.example.shop.utils.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@Validated
@RequestMapping("/manage")
public class manageController {

    @Autowired
    private ManageService manageService;

    @Autowired
    private MaterialService materialService;

    @PostMapping("/regist")
    public Result regist(@RequestBody ManageUser manageUser){
        if(null == manageUser){
            return Result.error("请输入用户名，密码");
        }
        if(StringUtils.isEmpty(manageUser.getName())){
            return Result.error("请输入用户名");
        }
        if(StringUtils.isEmpty(manageUser.getPassword())){
            return Result.error("请输入密码");
        }
        Map<String, Object> userByName = manageService.getUserByName(manageUser.getName());
        if(userByName != null){
            return Result.error("用户名:"+manageUser.getName()+" 已存在");
        }
        String password = manageUser.getPassword();
        String md5 = IdGenerator.getMD5(password);
        manageUser.setPassword(md5);
        manageUser.setDr(1);
        manageUser.setRoleId(1);
        manageUser.setCreateTime(new Date());
        int i = manageService.insertManageUser(manageUser);
        if(i == 1){
           return  Result.succ();
        }
        return Result.error("用户创建异常，请联系管理员");
    }

    @PostMapping("/login")
    public Result login(@RequestBody ManageUser manageUser){
        if(null == manageUser){
            return Result.error("请输入用户名，密码");
        }
        if(StringUtils.isEmpty(manageUser.getName())){
            return Result.error("请输入用户名");
        }
        if(StringUtils.isEmpty(manageUser.getPassword())){
            return Result.error("请输入密码");
        }
        Map<String, Object> userByName = manageService.getUserByName(manageUser.getName());
        if(userByName == null){
            return Result.error("用户:"+manageUser.getName()+" 不存在");
        }
        if(2 == (Integer) userByName.get("dr")){
            return Result.error("用户:"+manageUser.getName()+" 已失效，请重新注册");
        }
        String password = manageUser.getPassword();
        String md5 = IdGenerator.getMD5(password);
        if(!md5.equals((String)userByName.get("password"))){
            return Result.error("密码不正确");
        }
        return Result.succ("登录成功");
    }


    /**
     * 获取商品列表
     * @param tabType 商品分类
     * @param pageNum
     * @param pageSize
     * @param dr 失效/有效
     * @return
     */
    @GetMapping("getMaterialList")
    public Result getMaterialList(
            @RequestParam(required = false) Integer tabType,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) Integer dr
    ){
        List<Map<String, Object>> materialList = materialService.getMaterialList(tabType, pageNum, pageSize,dr);
        return Result.succ(materialList);
    }

    @GetMapping("getMaterialById")
    public Result getMaterialById(@RequestParam(required = true) Integer spuId){
        Map<String, Object> map = materialService.getMaterialById(spuId);
        return Result.succ(map);
    }

    /**
     * 增加商品
     * @return
     */
    @PostMapping("/addMaterial")
    public Result addMaterial(@RequestBody Material material){
        String check = checkMaterial(material);
        if(StringUtils.isNotEmpty(check)){
            return Result.error(check);
        }
        
        return null;
    }

    private String checkMaterial(Material material){
        if(material == null){
            return "无商品信息";
        }
        if(StringUtils.isEmpty(material.getTitle())){
            return "商品标题未填写";
        }
        if(StringUtils.isEmpty(material.getImages())){
            return "至少有一张商品轮播图";
        }
        if(material.getPrice() == null){
            return "商品价格未填写";
        }
        return null;
    }
}
