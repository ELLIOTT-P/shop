package com.example.shop.dao.provider;

import com.example.shop.utils.SqlUtils;

public class MaterialDaoProvider {

    public String getMaterialList(Integer tabType, Integer pageNum, Integer pageSize,Integer dr){
        String sql = "select * from material";
        if(tabType != null){
            sql += " where and tabType="+tabType;
        }
        if(dr != null){
            sql += " dr="+dr;
        }
        sql += SqlUtils.getPageLimit(pageNum,pageSize);
        return sql;
    }

    public String getMaterialById(Integer spuId){
        String sql = "select * from material where spuId ="+spuId;
        return sql;
    }

    public String getColumn(Integer type,Integer dr){
        String sql = "select * from columnData where type="+type;
        if(dr != null){
            sql += " and dr="+dr;
        }
        sql += " order by sort asc";
        return sql;
    }

    public String getTableType(Integer dr){
        String sql = "select * from tabType where 1=1";
        if(dr != null){
            sql += " and dr="+dr;
        }
        sql += " order by sort asc";
        return sql;
    }
}
