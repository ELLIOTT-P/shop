package com.example.shop.dao.provider;

import com.example.shop.utils.SqlUtils;

public class MaterialDaoProvider {

    public String getMaterialList(Integer tabType, Integer pageNum, Integer pageSize){
        String sql = "select * from material where dr = 1";
        if(tabType != null){
            sql += " and tabType="+tabType;
        }
        //sql += SqlUtils.getPageLimit(pageNum,pageSize);
        return sql;
    }
}
