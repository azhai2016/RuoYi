package com.ruoyi.minids.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.minids.domain.Production;
import com.ruoyi.minids.domain.ProductionCategory;
import com.ruoyi.minids.domain.Category;
import com.ruoyi.minids.mapper.CategoryMapper;
import com.ruoyi.minids.mapper.ProductionCategoryMapper;
import com.ruoyi.minids.service.IProductionCategoryService;

/**
 * 部门管理 服务实现
 * 
 * @author ruoyi
 */
@Service
public class ProductionCategoryServiceImpl implements IProductionCategoryService {
    @Autowired
    private ProductionCategoryMapper productionCategoryMapper;

    @Override
    @DataScope(categoryAlias = "d")
    public int deleteProductCategoryById(Long categoryId) {
        return productionCategoryMapper.deleteProductionCategoryId(categoryId);
    }

    @Override
    @DataScope(categoryAlias = "d")
    public int insertProductCategory(ProductionCategory productionCategory) {
        return productionCategoryMapper.insertProductionCategory(productionCategory);
    }

    @Override
    @DataScope(categoryAlias = "d")
    public int batchProductionCategory(List<ProductionCategory> productionCategory) {
        return productionCategoryMapper.batchProductionCategory(productionCategory);
    }

    @Override
    @DataScope(categoryAlias = "d")
    public int selectCountProductionCategoryByCategoryId(Long categoryId) {
        return productionCategoryMapper.selectCountProductionCategoryByCategoryId(categoryId);
    }

}
