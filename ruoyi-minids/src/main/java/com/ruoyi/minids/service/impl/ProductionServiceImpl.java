package com.ruoyi.minids.service.impl;

import com.ruoyi.minids.domain.Production;
import com.ruoyi.minids.mapper.ProductionMapper;
import com.ruoyi.minids.service.IProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductionServiceImpl implements IProductionService {
    @Autowired
    private ProductionMapper productionMapper;

    @Override
    public List<Production> selectProductList(Production production) {
        return productionMapper.selectProductList(production);
    }

    @Override
    public Production selectProductById(int id) {
        return productionMapper.selectProductById(id);
    }

    @Override
    public void deleteProductById(String saasId, String storeId, String spuId) {
        productionMapper.deleteProductById(saasId, storeId, spuId);
    }

    @Override
    public void deleteProductByIds(List<Production> list) {
        productionMapper.deleteProductByIds(list);
    }

    @Override
    public void updateProduct(Production production) {
        productionMapper.updateProduct(production);
    }

    @Override
    public void insertProduct(Production production) {
        productionMapper.insertProduct(production);
    }

    @Override
    public List<Production> selectProductAll() {
        return productionMapper.selectProductAll();
    }

    @Override
    public void checkProductDataScope(int productId) {

    }

}