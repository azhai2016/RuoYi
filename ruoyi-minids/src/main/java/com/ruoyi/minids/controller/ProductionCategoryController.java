package com.ruoyi.minids.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.page.TableDataInfo;
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
    public List<ProductionCategory> list(ProductionCategory productionCategory)
    {
        List<ProductionCategory> productionCategoryList = productCategoryService.selectCategoryList(productionCategory);
        return productionCategoryList;
    }


    @RequiresPermissions("production:category:add")
    @PostMapping("/add")
    public String addCategory() {
        return prefix + "/add";
    }

}