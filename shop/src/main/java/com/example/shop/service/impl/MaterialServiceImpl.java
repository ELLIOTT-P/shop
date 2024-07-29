package com.example.shop.service.impl;

import com.example.shop.dao.MaterialDao;
import com.example.shop.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialDao materialDao;

    @Override
    public List<Map<String, Object>> getMaterialList(Integer tabType, Integer pageNum, Integer pageSize) {
        List<Map<String, Object>> list = materialDao.getMaterialList(tabType,pageNum,pageSize);
        return list;
    }
}
