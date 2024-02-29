package com.ruoyi.minids.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;

import com.ruoyi.minids.domain.Production;
import com.ruoyi.minids.domain.ProductionCategory;
import com.ruoyi.minids.service.IProductionService;

import net.sf.ehcache.util.ProductInfo;

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

    @RequiresPermissions("minids:production:add")
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        // mmap.put("production",
        // productService.selectProductAll().stream().filter(r ->
        // !r.isAdmin()).collect(Collectors.toList()));
        // mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    @RequiresPermissions("minids:production:edit")
    @GetMapping("/edit/{productId}")
    public String edit(@PathVariable("productId") Long productId, ModelMap mmap) {
        productService.checkProductDataScope(productId);
        // mmap.put("product", productService.selectProductById(productId));
        return prefix + "/edit";
    }

    @RequiresPermissions("minids:production:edit")
    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysUser user) {
        // userService.checkUserAllowed(user);
        // userService.checkUserDataScope(user.getUserId());
        // if (!userService.checkLoginNameUnique(user)) {
        // return error("修改用户'" + user.getLoginName() + "'失败，登录账号已存在");
        // } else if (StringUtils.isNotEmpty(user.getPhonenumber()) &&
        // !userService.checkPhoneUnique(user)) {
        // return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
        // } else if (StringUtils.isNotEmpty(user.getEmail()) &&
        // !userService.checkEmailUnique(user)) {
        // return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        // }
        // user.setUpdateBy(getLoginName());
        // AuthorizationUtils.clearAllCachedAuthorizationInfo();
        // return toAjax(userService.updateUser(user));
        return null;
    }
}