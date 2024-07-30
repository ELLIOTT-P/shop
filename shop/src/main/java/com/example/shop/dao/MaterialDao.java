package com.example.shop.dao;

import com.example.shop.dao.provider.MaterialDaoProvider;
import com.example.shop.dao.provider.TestDaoProvider;
import com.example.shop.entity.Material;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface MaterialDao {

    @SelectProvider(type = MaterialDaoProvider.class, method = "getMaterialList")
    List<Map<String, Object>> getMaterialList(Integer tabType, Integer pageNum, Integer pageSize,Integer dr);

    @SelectProvider(type = MaterialDaoProvider.class, method = "getMaterialById")
    Map<String,Object> getMaterialById(Integer supId);

    @SelectProvider(type = MaterialDaoProvider.class, method = "getColumn")
    List<Map<String, Object>> getColumn(Integer type,Integer dr);

    @SelectProvider(type = MaterialDaoProvider.class, method = "getTableType")
    List<Map<String, Object>> getTableType(Integer dr);

    @SelectProvider(type = MaterialDaoProvider.class, method = "getSpuIdMax")
    Map<String,Integer> getSpuIdMax();

    @Insert("insert into material(spuId,thumb,title,price,originPrice,type,tabType,createTime,updateTime,dr,stock,images,imagesDetails) \n" +
            "vaules (#{spuId},#{thumb},#{title},#{price},#{originPrice},#{type},#{tabType},#{createTime},#{updateTime},#{dr},#{stock},#{images},#{imagesDetails}")
    int addMaterial(Material material);

    @Update("update material set thumb=#{thumb},title=#{title},price=#{price},originPrice=#{originPrice},type=#{type},tabType=#{tabType},\n" +
            "createTime=#{createTime},updateTime=#{updateTime},dr=#{dr},stock=#{stock},images=#{images},imagesDetails=#{imagesDetails} where spuId=#{spuId}")
    int updateMaterial(Material material);
}
