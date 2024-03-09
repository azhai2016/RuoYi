package com.ruoyi.minids.mapper;

import com.ruoyi.minids.domain.Shop;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * ShopMapper 接口
 */
@Mapper
public interface ShopMapper {

    // 自定义查询方法，如果需要的话
    List<Shop> selectShopList();

    Shop selectShopById(String shopId);

    int insertShop(Shop shop);

    int updateShop(Shop shop);

    int deleteShopById(String shopId);

    Shop checkShopNameUnique(Shop shop);
}