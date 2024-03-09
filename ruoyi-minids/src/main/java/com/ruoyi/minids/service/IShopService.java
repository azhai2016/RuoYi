package com.ruoyi.minids.service;

import com.ruoyi.minids.domain.Shop;
import java.util.List;

/**
 * ShopService 接口
 */
public interface IShopService {

    List<Shop> selectShopList();

    Shop selectShopById(String string);

    int insertShop(Shop shop);

    int updateShop(Shop shop);

    int deleteShopById(String shopId);

    boolean checkShopNameUnique(Shop shop);

}