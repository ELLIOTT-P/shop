package com.example.shop.dao.provider;

public class ManageDaoProvider {

    public String getUserByName(String name){
        String sql = "select * from manageUser where name = '"+name+"'";
        return sql;
    }

}
