package com.example.shop.dao;

import com.example.shop.dao.provider.TestDaoProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

@Mapper
public interface TestDao {

    @SelectProvider(type = TestDaoProvider.class, method = "getList")
    Object getList();
}
