/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 功能描述：排行榜 DTO 实体类
 *
 * @author 冯仕清
 * @since 2025-04-06
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "排行榜 DTO 实体类")
public class RankingDto {

    /**
     * 排行榜中的业务对象的ID，例如 帖子的ID、文章的ID
     */
    @Schema(description = "排行榜中的业务对象的ID，例如 帖子的ID、文章的ID", example = "doc0001")
    @NotBlank(message = "【排行榜中的业务对象的ID】不能为空")
    @Size(max = 20, message = "【排行榜中的业务对象的ID】长度不能超过20")
    private String bizId;


    /**
     * 总数量 (点赞数, 评分)
     */
    @Schema(description = "总数量 (点赞数, 评分)")
    private String totalQuantity;


    public RankingDto(String bizId, String totalQuantity) {
        this.bizId = bizId;
        this.totalQuantity = totalQuantity;
    }
}
