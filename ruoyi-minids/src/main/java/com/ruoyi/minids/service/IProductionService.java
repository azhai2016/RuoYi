package com.ruoyi.minids.service;

import java.util.List;
import com.ruoyi.minids.domain.Production;

public interface IProductionService {

    public List<Production> selectProductList(Production production);

    public Production selectProductById(int productId);

    public void deleteProductById(String saasId, String storeId, String spuId);

    public void deleteProductByIds(List<Production> list);

    public void updateProduct(Production production);

    public void insertProduct(Production production);

    public List<Production> selectProductAll();

    public void checkProductDataScope(int productId);

}
