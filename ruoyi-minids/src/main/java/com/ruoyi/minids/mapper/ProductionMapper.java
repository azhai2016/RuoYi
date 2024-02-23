package com.ruoyi.minids.mapper;

import com.ruoyi.minids.domain.Production;
import java.util.List;

/**
 * 商品信息 数据层
 * 
 * @author azhai
 */
public interface ProductionMapper {

        List<Production> selectProductList(Production production);

        List<Production> selectProductAll();

        Production selectProductById(String saasId, String storeId, String spuId);

        void deleteProductById(String saasId, String storeId, String spuId);

        void deleteProductByIds(List<Production> list);

        void updateProduct(Production production);

        void insertProduct(Production production);

}