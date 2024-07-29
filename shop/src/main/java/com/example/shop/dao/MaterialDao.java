package com.example.shop.dao;

import com.example.shop.dao.provider.MaterialDaoProvider;
import com.example.shop.dao.provider.TestDaoProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

@Mapper
public interface MaterialDao {

    @SelectProvider(type = MaterialDaoProvider.class, method = "getMaterialList")
    List<Map<String, Object>> getMaterialList(Integer tabType, Integer pageNum, Integer pageSize);
}
