package com.example.shop.service;

import com.example.shop.entity.ManageUser;

import java.util.Map;

public interface ManageService {

    Map<String,Object> getUserByName(String name);

    int insertManageUser(ManageUser manageUser);
}
