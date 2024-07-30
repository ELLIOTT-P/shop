package com.example.shop.dao;

import com.example.shop.dao.provider.ManageDaoProvider;
import com.example.shop.entity.ManageUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Map;

@Mapper
public interface ManageDao {

    @SelectProvider(type = ManageDaoProvider.class, method = "getUserByName")
    Map<String,Object> getUserByName(String name);

    @Insert("insert into manageUser(name,password,phone,roleId,dr,createTime,updateTime) " +
            "values (#{name},#{password},#{phone},#{roleId},#{dr},#{createTime},#{updateTime})")
    int insertUser(ManageUser manageUser);
}
