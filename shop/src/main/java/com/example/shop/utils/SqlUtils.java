package com.example.shop.utils;

public class SqlUtils {

    public static String getPageLimit(Integer pageNum,Integer pageSize){
        String limit = " limit "+pageSize + " offset "+(pageNum-1)*pageSize;
        return limit;
    }
}
