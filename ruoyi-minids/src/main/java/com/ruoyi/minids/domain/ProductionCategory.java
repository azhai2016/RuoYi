package com.ruoyi.minids.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * azhai
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductionCategory extends BaseEntity implements Serializable {
    private Long categoryId;
    private Long parentId;
    private String ancestors;
    private String categoryName;
    private Integer orderNum;
    private String status;
    private String delFlag;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private Long excludeId;

    @JsonIgnore
    public Long getExcludeId() {
        return excludeId;
    }

    public void setExcludeId(Long excludeId) {
        this.excludeId = excludeId;
    }
}