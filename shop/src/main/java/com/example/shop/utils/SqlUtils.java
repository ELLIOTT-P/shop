package com.example.shop.utils;

public class SqlUtils {

    public static String getPageLimit(Integer pageNum,Integer pageSize){
        String limit = " limit "+(pageNum-1)*pageSize + " offset "+pageNum*pageSize;
        return limit;
    }
}
