package com.example.shop.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Material implements Serializable {

    private Integer id;

    private Integer spuId;

    private String thumb;

    private String title;

    private BigDecimal price;

    private BigDecimal originPrice;

    private Integer type;//商品分类

    private Integer tabType;//列表分类

    private Date createTime;

    private Date updateTime;

    private Integer dr;

    private Integer stock;//库存

    private String images;//商品详情轮播

    private String imagesDetails;//商品详情图

    private String[] imagesDetailsArr;

    private String[] imagesArr;
}
