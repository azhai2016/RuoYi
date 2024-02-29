package com.ruoyi.minids.domain;

import java.io.Serializable;
import java.sql.Date;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * azhai
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductionCategory extends BaseEntity implements Serializable {
    private Long proctuctId;
    private Long categoryId;
}