package com.ruoyi.minids.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.minids.domain.Category;
import com.ruoyi.minids.domain.Shop;
import com.ruoyi.minids.mapper.ShopMapper;
import com.ruoyi.minids.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ShopServiceImpl 类
 */
@Service
public class ShopServiceImpl implements IShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public List<Shop> selectShopList() {
        return shopMapper.selectShopList();
    }

    @Override
    public Shop selectShopById(String shopId) {
        return shopMapper.selectShopById(shopId);
    }

    @Override
    public int insertShop(Shop shop) {
        return shopMapper.insertShop(shop);
    }

    @Override
    public int updateShop(Shop shop) {
        return shopMapper.updateShop(shop);
    }

    @Override
    public int deleteShopById(String shopId) {
        return shopMapper.deleteShopById(shopId);
    }

    @Override
    public boolean checkShopNameUnique(Shop shop) {
        String shopId = StringUtils.isNull(shop.getShopId()) ? "" : shop.getShopId();
        Shop info = shopMapper.selectShopById(shopId);
        if (StringUtils.isNotNull(info) && info.getShopId() != shopId) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}