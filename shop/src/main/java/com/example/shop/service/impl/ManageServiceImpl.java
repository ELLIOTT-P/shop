package com.example.shop.service.impl;

import com.example.shop.dao.ManageDao;
import com.example.shop.entity.ManageUser;
import com.example.shop.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private ManageDao manageDao;

    @Override
    public Map<String, Object> getUserByName(String name) {
        return manageDao.getUserByName(name);
    }

    @Override
    public int insertManageUser(ManageUser manageUser) {
        return manageDao.insertUser(manageUser);
    }
}
