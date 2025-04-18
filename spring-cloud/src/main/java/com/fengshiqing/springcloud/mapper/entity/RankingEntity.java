/*
 * Copyright (c) 2025. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.mapper.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 功能描述：排行榜 Entity 实体类
 *
 * @author fengshiqing
 * @since 2025-04-04
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "排行榜 Entity 实体类")
public class RankingEntity {

    /**
     * 自增主键
     */
    @Schema(description = "自增主键")
    private Long id;


    /**
     * 榜单类型，比如：like(点赞)、collect(收藏)、transmit(转发)、comment(评论)、score(得分)
     */
    @Schema(description = "榜单类型，比如：like(点赞)、collect(收藏)、transmit(转发)、comment(评论)")
    @NotBlank(message = "【榜单类型】不能为空")
    @Size(max = 20, message = "【榜单类型】长度不能超过20")
    private String rankType;


    /**
     * 排行榜中的业务对象的ID，例如 帖子的ID、文章的ID
     */
    @Schema(description = "排行榜中的业务对象的ID，例如 帖子的ID、文章的ID")
    @NotBlank(message = "【排行榜中的业务对象的ID】不能为空")
    @Size(max = 20, message = "【排行榜中的业务对象的ID】长度不能超过20")
    private String bizId;


    /**
     * 数量变化 (点赞+1, 取消点赞-1, 评分+10)
     */
    @Schema(description = "数量变化 (点赞+1, 取消点赞-1, 评分+10)")
    private Integer quantity;


    /**
     * 总数量 (点赞数, 评分)
     */
    @Schema(description = "总数量 (点赞数, 评分)")
    private Integer totalQuantity;


    /**
     * 操作时间
     */
    @Schema(description = "操作时间")
    private Date lastUpdateTime;


    public RankingEntity(String rankType, String bizId, Integer quantity) {
        this.rankType = rankType;
        this.bizId = bizId;
        this.quantity = quantity;
    }
}
