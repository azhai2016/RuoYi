package com.ruoyi.minids.service;

import java.util.List;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.minids.domain.Production;
import com.ruoyi.minids.domain.ProductionCategory;
import com.ruoyi.minids.domain.Category;

/**
 * 部门管理 服务层
 * 
 * @author ruoyi
 */
public interface IProductionCategoryService {

    public int batchProductionCategory(List<ProductionCategory> productionCategory);

    public int selectCountProductionCategoryByCategoryId(Long categoryId);

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteProductCategoryById(Long categoryId);

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertProductCategory(ProductionCategory productionCategory);

}
