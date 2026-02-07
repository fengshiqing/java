/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 功能描述：分页查询时，用到的分页参数
 *
 * @author 冯仕清
 * @since 2025-08-27
 */
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "分页查询时，用到的分页参数")
public class PagedReq {

    @Schema(description = "当前页码")
    @NotNull(message = "【当前页码 pageNum 不能为空】")
    @Min(value = 1, message = "【当前页码 pageNum 不能小于 1】")
    private Integer pageNum;

    @Schema(description = "每页的数据条数")
    @NotNull(message = "【每页的数据条数 pageSize 不能为空】")
    @Min(value = 1, message = "【每页的数据条数 pageSize 不能小于 1】")
    private Integer pageSize;

    @Schema(description = "偏移量，sql语句中 limit 用到此参数")
    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }

}
