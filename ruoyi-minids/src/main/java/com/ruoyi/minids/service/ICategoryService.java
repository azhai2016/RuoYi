package com.ruoyi.minids.service;

import java.util.List;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.minids.domain.Production;
import com.ruoyi.minids.domain.Category;

/**
 * 部门管理 服务层
 * 
 * @author ruoyi
 */
public interface ICategoryService {

    public List<Category> selectCategoryAll();

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    public List<Category> selectCategoryList(Category productionCategory);

    /**
     * 查询部门管理树
     * 
     * @param 商品分类信息
     * @return 所有商品分类信息
     */
    public List<Ztree> selectCategoryTree(Category productionCategory);

    /**
     * 查询部门管理树（排除下级）
     * 
     * @param dept 部门信息
     * @return 所有部门信息
     */
    public List<Ztree> selectCategoryTreeExcludeChild(Category productionCategory);

    /**
     * 根据父部门ID查询下级部门数量
     * 
     * @param parentId 父部门ID
     * @return 结果
     */
    public int selectCategoryCount(Long parentId);

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkCategoryExistProduction(Long categoryId);

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteCategoryById(Long categoryId);

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int insertCategory(Category productionCategory);

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int updateCategory(Category productionCategory);

    /**
     * 根据部门ID查询信息
     * 
     * @param CategoryId 部门ID
     * @return 部门信息
     */
    public Category selectCategoryById(Long categoryId);

    /**
     * 根据ID查询所有子部门（正常状态）
     * 
     * @param deptId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenCategoryById(Long dategoryId);

    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public boolean checkCategoryNameUnique(Category productionCategory);

    /**
     * 校验部门是否有数据权限
     * 
     * @param deptId 部门id
     */
    public void checkCategoryDataScope(Long categoryId);

    public List<Ztree> prodCategoryTreeData(Production production);

}
