package com.ruoyi.minids.domain;

import java.io.Serializable;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * azhai
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Production extends BaseEntity implements Serializable {

    private String saasId = "88888";
    private String storeId;
    private String spuId;
    private String title;
    private String primaryImage;
    private int available;
    private double minSalePrice;
    private double minLinePrice;
    private double maxSalePrice;
    private double maxLinePrice;
    private int spuStockQuantity;
    private int soldNum;
    private int isPutOnSale;
    private String Video = "255";
    private String Etitle = "255";
    private String titlePrefixTags;
    private String thumb;
    private int CategoryId;

}
