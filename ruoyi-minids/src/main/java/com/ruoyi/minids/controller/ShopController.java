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
import com.ruoyi.minids.domain.Shop;
import com.ruoyi.minids.service.IShopService;

@Controller
@RequestMapping("/minids/shop")
public class ShopController extends BaseController {
    private String prefix = "minids/shop";

    @Autowired
    private IShopService shopService;

    @RequiresPermissions("minids:shop:list")
    @GetMapping()
    public String category() {
        return prefix + "/view";
    }

    @RequiresPermissions("minids:shop:list")
    @PostMapping("/list")
    @ResponseBody
    public List<Shop> list() {
        List<Shop> shopList = shopService.selectShopList();
        return shopList;
    }

    /**
     * 校验店铺名称
     */
    @PostMapping("/checkShopNameUnique")
    @ResponseBody
    public boolean checkShopNameUnique(Shop shop) {
        return shopService.checkShopNameUnique(shop);
    }

    @GetMapping("/add/{shopId}")
    public String add(@PathVariable("shopId") String shopId, ModelMap mmap) {
        if (!getSysUser().isAdmin()) {
            shopId = getSysUser().getDeptId().toString();
        }
        Object obj = shopService.selectShopById(shopId);
        mmap.put("shop", obj);
        return prefix + "/add";
    }

    @GetMapping("/edit/{shopId}")
    public String edit(@PathVariable("shopId") String shopId, ModelMap mmap) {

        Object obj = shopService.selectShopById(shopId);
        mmap.put("shop", obj);
        return prefix + "/edit";
    }

    @Log(title = "店铺管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("minids:shop:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Shop shop) {
        if (!shopService.checkShopNameUnique(shop)) {
            return error("新增类别'" + shop.getShopName() + "'失败，部门名称已存在");
        }
        return toAjax(shopService.insertShop(shop));
    }

    @Log(title = "店铺管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("minids:shop:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Shop shop) {
        if (!shopService.checkShopNameUnique(shop)) {
            return error("修改店铺'" + shop.getShopName() + "'失败，名称已存在");
        }
        return toAjax(shopService.updateShop(shop));
    }

    @Log(title = "店铺管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("minids:shop:add")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult removeSave(@Validated Shop shop) {
        if (!shopService.checkShopNameUnique(shop)) {
            return error("删除类别'" + shop.getShopName() + "'失败");
        }
        return toAjax(shopService.deleteShopById(shop.getShopId()));
    }

}