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
import com.ruoyi.minids.domain.Category;
import com.ruoyi.minids.mapper.CategoryMapper;
import com.ruoyi.minids.service.ICategoryService;

import net.sf.ehcache.util.ProductInfo;

/**
 * 部门管理 服务实现
 * 
 * @author ruoyi
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper productionCategoryMapper;

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    @DataScope(categoryAlias = "d")
    public List<Category> selectCategoryList(Category productionCategory) {
        return productionCategoryMapper.selectCategoryList(productionCategory);
    }

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    // @DataScope(categoryAlias = "d")
    public List<Category> selectCategoryAll() {
        return productionCategoryMapper.selectCategoryAll();
    }

    /**
     * 查询部门管理树
     * 
     * @param dept 部门信息
     * @return 所有部门信息
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<Ztree> selectCategoryTree(Category productionCategory) {
        List<Category> categoryList = productionCategoryMapper.selectCategoryList(productionCategory);
        System.out.println(categoryList);
        List<Ztree> ztrees = initZtree(categoryList, null);
        return ztrees;
    }

    /**
     * 查询部门管理树（排除下级）
     * 
     * @param deptId 部门ID
     * @return 所有部门信息
     */
    @Override
    @DataScope(categoryAlias = "d")
    public List<Ztree> selectCategoryTreeExcludeChild(Category productionCategory) {
        Long excludeId = productionCategory.getExcludeId();
        List<Category> prodCategoryList = productionCategoryMapper.selectCategoryList(productionCategory);
        if (excludeId.intValue() > 0) {
            prodCategoryList.removeIf(d -> d.getCategoryId().intValue() == excludeId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), excludeId + ""));
        }
        List<Ztree> ztrees = initZtree(prodCategoryList, null);
        return ztrees;
    }

    /**
     * 根据角色ID查询部门（数据权限）
     *
     * @param role 角色对象
     * @return 部门列表（数据权限）
     */
    @Override
    public List<Ztree> prodCategoryTreeData(Production production) {
        int categoryId = production.getCategoryId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<Category> pcList = SpringUtils.getAopProxy(this).selectCategoryList(new Category());
        System.out.println(pcList);
        if (StringUtils.isNotNull(categoryId)) {
            List<String> categoryList = productionCategoryMapper.selectProductionCategoryTree(categoryId);
            ztrees = initZtree(pcList, categoryList);
        } else {
            ztrees = initZtree(pcList);
        }
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param categoryList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Category> categoryList) {
        return initZtree(categoryList);
    }

    /**
     * 对象转部门树
     *
     * @param deptList     部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Category> pcList, List<String> pdcategoryList) {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(pdcategoryList);
        for (Category pc : pcList) {
            if (UserConstants.DEPT_NORMAL.equals(pc.getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(pc.getCategoryId());
                ztree.setpId(pc.getParentId());
                ztree.setName(pc.getCategoryName());
                ztree.setTitle(pc.getCategoryName());
                if (isCheck) {
                    ztree.setChecked(pcList.contains(pc.getCategoryId() + pc.getCategoryName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 根据父部门ID查询下级部门数量
     * 
     * @param parentId 部门ID
     * @return 结果
     */
    @Override
    public int selectCategoryCount(Long parentId) {
        Category productionCategory = new Category();
        productionCategory.setParentId(parentId);
        return productionCategoryMapper.selectProductionCategoryCount(productionCategory);
    }

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkCategoryExistProduction(Long categoryId) {
        int result = productionCategoryMapper.checkCategoryExistProduction(categoryId);
        return result > 0;
    }

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long categoryId) {
        return productionCategoryMapper.deleteCategoryById(categoryId);
    }

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertCategory(Category productionCategory) {
        Category info = productionCategoryMapper.selectCategoryById(productionCategory.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus())) {
            throw new ServiceException("部门停用，不允许新增");
        }
        productionCategory.setAncestors(info.getAncestors() + "," + productionCategory.getParentId());
        return productionCategoryMapper.insertCategory(productionCategory);
    }

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateCategory(Category productionCategory) {
        Category newParentCategroy = productionCategoryMapper
                .selectCategoryById(productionCategory.getParentId());
        Category oldDept = selectCategoryById(productionCategory.getCategoryId());
        if (StringUtils.isNotNull(newParentCategroy) && StringUtils.isNotNull(oldDept)) {
            String newAncestors = newParentCategroy.getAncestors() + "," + newParentCategroy.getCategoryId();
            String oldAncestors = oldDept.getAncestors();
            productionCategory.setAncestors(newAncestors);
            updateDeptChildren(productionCategory.getCategoryId(), newAncestors, oldAncestors);
        }
        int result = productionCategoryMapper.updateCategory(productionCategory);
        if (UserConstants.DEPT_NORMAL.equals(productionCategory.getStatus()) && StringUtils.isNotEmpty(
                productionCategory.getAncestors())
                && !StringUtils.equals("0", productionCategory.getAncestors())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatusNormal(productionCategory);
        }
        return result;
    }

    /**
     * 修改该部门的父级部门状态
     * 
     * @param productionCategory 当前部门
     */
    private void updateParentDeptStatusNormal(Category productionCategory) {
        String ancestors = productionCategory.getAncestors();
        Long[] categoryIds = Convert.toLongArray(ancestors);
        productionCategoryMapper.updateCategoryStatusNormal(categoryIds);
    }

    /**
     * 修改子元素关系
     * 
     * @param deptId       被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {
        List<Category> children = productionCategoryMapper.selectChildrenCategoryById(deptId);
        for (Category child : children) {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0) {
            productionCategoryMapper.updateCategoryChildren(children);
        }
    }

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public Category selectCategoryById(Long categoryId) {
        return productionCategoryMapper.selectCategoryById(categoryId);
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     * 
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Override
    public int selectNormalChildrenCategoryById(Long categoryId) {
        return productionCategoryMapper.selectNormalChildrenCategoryById(categoryId);
    }

    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public boolean checkCategoryNameUnique(Category productionCategory) {
        Long deptId = StringUtils.isNull(productionCategory.getCategoryId()) ? -1L : productionCategory.getCategoryId();
        Category info = productionCategoryMapper.checkCategoryNameUnique(
                productionCategory.getCategoryName(), productionCategory.getParentId());
        if (StringUtils.isNotNull(info) && info.getCategoryId().longValue() != deptId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验部门是否有数据权限
     * 
     * @param deptId 部门id
     */
    @Override
    public void checkCategoryDataScope(Long categoryId) {
        if (!SysUser.isAdmin(ShiroUtils.getUserId())) {
            Category dept = new Category();
            dept.setCategoryId(categoryId);
            List<Category> depts = SpringUtils.getAopProxy(this).selectCategoryList(dept);
            if (StringUtils.isEmpty(depts)) {
                throw new ServiceException("没有权限访问部门数据！");
            }
        }
    }

}
