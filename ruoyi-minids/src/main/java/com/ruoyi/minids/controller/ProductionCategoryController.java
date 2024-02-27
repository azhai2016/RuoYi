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
import com.ruoyi.minids.domain.ProductionCategory;
import com.ruoyi.minids.service.IProductionCategoryService;

@Controller
@RequestMapping("/minids/production/category")
public class ProductionCategoryController extends BaseController {
    private String prefix = "minids/production/category";

    @Autowired
    private IProductionCategoryService productCategoryService;

    @RequiresPermissions("production:category")
    @GetMapping()
    public String category() {
        return prefix + "/view";
    }

    @RequiresPermissions("production:category")
    @PostMapping("/list")
    @ResponseBody
    public List<ProductionCategory> list(ProductionCategory productionCategory) {
        List<ProductionCategory> productionCategoryList = productCategoryService.selectCategoryList(productionCategory);
        return productionCategoryList;
    }

    /**
     * 加载部门列表树
     */
    @RequiresPermissions("production:category:list")
    @GetMapping("/categoryTreeData")
    @ResponseBody
    public List<Ztree> categoryTreeData() {
        List<Ztree> ztrees = productCategoryService.selectCategoryTree(new ProductionCategory());
        return ztrees;
    }

    /**
     * 选择部门树
     * 
     * @param categoryId 部门ID
     */
    @RequiresPermissions("production:category:list")
    @GetMapping("/selectCategoryTree/{categoryId}")
    public String selectDeptTree(@PathVariable("categoryId") Long categoryId, ModelMap mmap) {
        Object obj = productCategoryService.deleteCategoryById(categoryId);
        System.out.println(obj);
        mmap.put("category", obj);
        return prefix + "/categoryTree";
    }

    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        if (!getSysUser().isAdmin()) {
            parentId = getSysUser().getDeptId();
        }
        Object obj = productCategoryService.selectCategoryById(parentId);
        System.out.println(obj);
        mmap.put("category", obj);
        return prefix + "/add";
    }

    @Log(title = "类别管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("production:category:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated ProductionCategory productionCategory) {
        if (!productCategoryService.checkCategoryNameUnique(productionCategory)) {
            return error("新增类别'" + productionCategory.getCategoryName() + "'失败，部门名称已存在");
        }
        productionCategory.setCreateBy(getLoginName());
        return toAjax(productCategoryService.insertCategory(productionCategory));
    }

}