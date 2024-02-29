package com.ruoyi.minids.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.minids.domain.Category;
import com.ruoyi.minids.service.ICategoryService;

@Controller
@RequestMapping("/minids/production/category")
public class CategoryController extends BaseController {
    private String prefix = "minids/production/category";

    @Autowired
    private ICategoryService productCategoryService;

    @RequiresPermissions("production:category")
    @GetMapping()
    public String category() {
        return prefix + "/view";
    }

    @RequiresPermissions("production:category")
    @PostMapping("/list")
    @ResponseBody
    public List<Category> list(Category productionCategory) {
        List<Category> productionCategoryList = productCategoryService.selectCategoryList(productionCategory);
        return productionCategoryList;
    }

    /**
     * 加载分类列表树
     */
    @RequiresPermissions("production:category:list")
    @GetMapping("/categoryTreeData")
    @ResponseBody
    public List<Ztree> categoryTreeData() {
        List<Ztree> ztrees = productCategoryService.selectCategoryTree(new Category());
        return ztrees;
    }

    /**
     * 校验分类名称
     */
    @PostMapping("/checkCategoryNameUnique")
    @ResponseBody
    public boolean checkCategoryNameUnique(Category productionCategory) {
        return productCategoryService.checkCategoryNameUnique(productionCategory);
    }

    /**
     * 选择部门树
     * 
     * @param deptId    部门ID
     * @param excludeId 排除ID
     */
    @GetMapping(value = { "/selectCategoryTree/{categoryId}", "/selectCategoryTree/{categoryId}/{excludeId}" })
    public String selectDeptTree(@PathVariable("categoryId") Long deptId,
            @PathVariable(value = "excludeId", required = false) Long excludeId, ModelMap mmap) {
        mmap.put("category", productCategoryService.selectCategoryById(deptId));
        mmap.put("excludeId", excludeId);
        return prefix + "/tree";
    }

    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        if (!getSysUser().isAdmin()) {
            parentId = getSysUser().getDeptId();
        }
        Object obj = productCategoryService.selectCategoryById(parentId);

        mmap.put("category", obj);
        return prefix + "/add";
    }

    @GetMapping("/edit/{parentId}")
    public String edit(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        if (!getSysUser().isAdmin()) {
            parentId = getSysUser().getDeptId();
        }
        Object obj = productCategoryService.selectCategoryById(parentId);
        mmap.put("category", obj);
        return prefix + "/edit";
    }

    @Log(title = "类别管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("production:category:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Category productionCategory) {
        if (!productCategoryService.checkCategoryNameUnique(productionCategory)) {
            return error("新增类别'" + productionCategory.getCategoryName() + "'失败，部门名称已存在");
        }
        productionCategory.setCreateBy(getLoginName());
        return toAjax(productCategoryService.insertCategory(productionCategory));
    }

    @Log(title = "类别管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("production:category:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Category productionCategory) {
        if (!productCategoryService.checkCategoryNameUnique(productionCategory)) {
            return error("修改类别'" + productionCategory.getCategoryName() + "'失败，名称已存在");
        }
        productionCategory.setCreateBy(getLoginName());
        return toAjax(productCategoryService.updateCategory(productionCategory));
    }

    @Log(title = "类别管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("production:category:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult removeSave(@Validated Category productionCategory) {
        if (!productCategoryService.checkCategoryNameUnique(productionCategory)) {
            return error("删除类别'" + productionCategory.getCategoryName() + "'失败");
        }
        productionCategory.setCreateBy(getLoginName());
        return toAjax(productCategoryService.deleteCategoryById(productionCategory.getCategoryId()));
    }

    /**
     * 删除
     */
    @Log(title = "类别管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("production:category:remove")
    @GetMapping("/remove/{categoryId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("categoryId") Long categoryId) {
        if (productCategoryService.selectCategoryCount(categoryId) > 0) {
            return AjaxResult.warn("存在下级,不允许删除");
        }
        if (productCategoryService.checkCategoryExistProduction(categoryId)) {
            return AjaxResult.warn("商品信息已绑定,不允许删除");
        }
        productCategoryService.checkCategoryDataScope(categoryId);
        return toAjax(productCategoryService.deleteCategoryById(categoryId));
    }

    /**
     * 加载部门列表树（排除下级）
     */
    @GetMapping("/treeData/{excludeId}")
    @ResponseBody
    public List<Ztree> treeDataExcludeChild(@PathVariable(value = "excludeId", required = false) Long excludeId) {
        Category productionCategory = new Category();
        productionCategory.setExcludeId(excludeId);
        List<Ztree> ztrees = productCategoryService.selectCategoryTreeExcludeChild(productionCategory);
        return ztrees;
    }

}