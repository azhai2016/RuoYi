package com.ruoyi.minids.domain;

import java.io.Serializable;
import java.sql.Date;

import com.ruoyi.common.core.domain.BaseEntity;

import lombok.Data;

/**
 * Shop 实体类
 */
@Data
public class Shop extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 店铺ID */
    private String shopId;

    /** 店铺名称 */
    private String shopName;

    /** 状态 */
    private String status;

    /** 说明信息 */
    private String description;

    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    // 省略构造器、getter、setter 等方法
}