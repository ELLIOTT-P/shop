package com.example.shop.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ManageUser implements Serializable {

    private Integer id;

    private String name;

    private String password;

    private String phone;

    private Integer roleId;

    private Integer dr;

    private Date createTime;

    private Date updateTime;

}
