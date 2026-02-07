package com.fengshiqing.springcloud.controller;

import com.fengshiqing.common.bean.Resp;
import com.fengshiqing.common.bean.RespData;
import com.fengshiqing.springcloud.service.RankingCommonService;
import com.fengshiqing.springcloud.service.RankingService;
import com.fengshiqing.springcloud.service.dto.RankingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述：排行榜Controller。
 *
 * @author 冯仕清
 * @since 2025-04-04
 */
@Tag(name = "排行榜", description = "排行榜")
@AllArgsConstructor
@RestController
public class RankingController {

    private final RankingService rankingService;

    private final RankingCommonService rankingCommonService;


    /**
     * 功能描述：刷新榜单
     *
     * @param rankType 榜单类型
     * @param bizId 排行榜中的业务对象的ID，例如 帖子的ID、文章的ID
     *
     * @return Resp
     */
    @Operation(summary = "刷新榜单", description = "刷新榜单")
    @PutMapping(value = "/ranking/v1/update/{rankType}")
    public Resp updateRanking(@PathVariable("rankType") String rankType, @RequestParam @Valid @NotBlank String bizId) {
        // 1、刷新榜单
        rankingService.updateRanking(rankType, bizId);

        // 2、数据更新到缓存中
        rankingCommonService.updateRankingToRedis(rankType, bizId);

        return new Resp(200, "操作成功");
    }


    /**
     * 功能描述：查询某个榜单 的前 n 名。
     *
     * @param rankType 榜单类型
     *
     * @return Resp
     */
    @Operation(summary = "查询某个榜单", description = "查询某个榜单")
    @PutMapping(value = "/ranking/v1/query/{rankType}")
    public Resp selectAndCalcRanking(@PathVariable("rankType") String rankType) {
        List<RankingDto> rankingEntityList = rankingService.selectAndCalcRanking(rankType);
        return new RespData<>(rankingEntityList);
    }

}
