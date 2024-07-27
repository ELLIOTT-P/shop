package com.example.shop.service.impl;

import com.example.shop.dao.TestDao;
import com.example.shop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;
    @Override
    public Object getList() {
        Object list = testDao.getList();
        return list;
    }
}
