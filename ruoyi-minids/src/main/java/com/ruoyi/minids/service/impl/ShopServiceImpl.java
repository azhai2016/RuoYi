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
    public Shop selectShopById(Long shopId) {
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
    public int deleteShopById(Long shopId) {
        return shopMapper.deleteShopById(shopId);
    }

    @Override
    public boolean checkShopNameUnique(Shop shop) {
        Long shopId = StringUtils.isNull(shop.getShopId()) ? -1L : shop.getShopId();
        Shop info = shopMapper.selectShopById(shopId);
        if (StringUtils.isNotNull(info) && info.getShopId().longValue() != shopId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}