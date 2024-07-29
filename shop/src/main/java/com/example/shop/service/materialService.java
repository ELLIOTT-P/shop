package com.example.shop.service;

import java.util.List;
import java.util.Map;

public interface materialService {
    //按分类获取列表数据
    List<Map<String,Object>> getMaterialList(Integer tabType);
}
