package com.example.shop.service.impl;

import com.example.shop.dao.MaterialDao;
import com.example.shop.entity.Material;
import com.example.shop.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialDao materialDao;

    @Override
    public List<Map<String, Object>> getMaterialList(Integer tabType, Integer pageNum, Integer pageSize, Integer dr) {
        List<Map<String, Object>> list = materialDao.getMaterialList(tabType,pageNum,pageSize,dr);
        List<Map<String, Object>> listResult = new ArrayList<>();
        if(list != null && list.size() > 0){
            for (Map<String, Object> map:list) {
                if(map.containsKey("images")){
                    String images = (String)map.get("images");
                    String[] split = images.split(",");
                    map.put("images",split);
                }
                if(map.containsKey("imagesDetails")){
                    String imagesDetails = (String)map.get("imagesDetails");
                    String[] split = imagesDetails.split(",");
                    map.put("imagesDetails",split);
                }
                listResult.add(map);
            }
        }
        return list;
    }

    @Override
    public Map<String, Object> getMaterialById(Integer supId) {
        Map<String, Object> map = materialDao.getMaterialById(supId);
        if(map.containsKey("images")){
            String images = (String)map.get("images");
            String[] split = images.split(",");
            map.put("images",split);
        }
        if(map.containsKey("imagesDetails")){
            String imagesDetails = (String)map.get("imagesDetails");
            String[] split = imagesDetails.split(",");
            map.put("imagesDetails",split);
        }
        return map;
    }

    @Override
    public List<Map<String, Object>> getColumn(Integer type,Integer dr) {
        List<Map<String, Object>> column = materialDao.getColumn(type, dr);
        return column;
    }

    @Override
    public List<Map<String, Object>> getTableType(Integer dr) {
        List<Map<String, Object>> tabType = materialDao.getTableType(dr);
        return tabType;
    }

    @Override
    public Map<String, Object> getHome() {
        List<Map<String, Object>> column = materialDao.getColumn(1, 1);
        List<Map<String, Object>> tableType = materialDao.getTableType(1);
        Map<String, Object> resultMap = new HashMap<>();
        if(column != null && column.size() > 0){
            List<String> swiper = new ArrayList<>();
            for(Map<String, Object> map : column){
                if(map.containsKey("image")){
                   String image = (String)map.get("image");
                    swiper.add(image);
                }
            }
            resultMap.put("swiper",swiper);
        }
        if(tableType != null && tableType.size() > 0){
            List<Map<String,Object>> tabList = new ArrayList<>();
            for(Map<String, Object> map : tableType){
                Map<String, Object> tmpMap = new HashMap<>();
                String text = (String)map.get("tabTypeName");
                Integer key = (Integer)map.get("tabTypeId");
                tmpMap.put("text",text);
                tmpMap.put("key",key);
                tabList.add(tmpMap);
            }
            resultMap.put("tabList",tabList);
        }
        return resultMap;
    }

    @Override
    @Transactional
    public int insertMaterial(Material material) {
        Map<String, Integer> spuIdMax = materialDao.getSpuIdMax();
        if(spuIdMax != null){
            Integer spuId = spuIdMax.get("spuId");
            material.setSpuId(spuId++);
        }
        material.setDr(1);
        material.setCreateTime(new Date());
        return materialDao.addMaterial(material);
    }

    @Override
    @Transactional
    public int updateMaterial(Material material) {
        material.setUpdateTime(new Date());
        return materialDao.updateMaterial(material);
    }
}
