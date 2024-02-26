package com.ruoyi.minids.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.ruoyi.minids.domain.Production;
import com.ruoyi.minids.domain.ProductionCategory;
import com.ruoyi.minids.service.IProductionService;
import com.ruoyi.minids.service.IProductionCategoryService;

@Controller
@RequestMapping("/minids/production")
public class ProductionController extends BaseController {
    private String prefix = "minids/production";

    @Autowired
    private IProductionService productService;


    @RequiresPermissions("minids:production:view")
    @GetMapping()
    public String production() {
        return prefix + "/production";
    }

    @RequiresPermissions("minids:production:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo lists(Production production) {
        startPage();
        List<Production> list = productService.selectProductAll();
        return getDataTable(list);
    }

    // @RequiresPermissions("minids:production:list")
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        // mmap.put("roles", roleService.selectRoleAll().stream().filter(r ->
        // !r.isAdmin()).collect(Collectors.toList()));
        // mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }
}