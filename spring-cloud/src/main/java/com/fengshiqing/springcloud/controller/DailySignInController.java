package com.fengshiqing.springcloud.controller;

import com.fengshiqing.common.model.Resp;
import com.fengshiqing.springcloud.service.DailySignInService;
import com.fengshiqing.springcloud.service.dto.DailySignVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：签到Controller，用于日常签到。
 * 设计思路：这个是按月统计的。一个月为一个周期，一个用户一年也就产生12条数据。如果需要统计一年的签到数据，就查询这一年的12条数据，然后处理下就行了。
 *
 * @author 冯仕清
 * @since 2023-09-09
 */
@Tag(name = "签到管理", description = "支持当日签到、以及补签")
@AllArgsConstructor
@RestController
@Validated
public class DailySignInController {

    private final DailySignInService dailySignInService;

    /**
     * 功能描述：日常签到。当月签到，只能签到当前月份，不支持签到其他月份。支持补签，也只能补签当前月份。
     *
     * @param dailySignVo 入参
     * @return Resp
     */
    @Operation(summary = "日常签到", description = "当月签到，只能签到当前月份，不支持签到其他月份。支持补签，也只能补签当前月份。")
    @PutMapping(value = "/api/v1/daily-sign-in")
    public Resp dailySignIn(@RequestBody @Valid DailySignVo dailySignVo) {
        dailySignInService.dailySignIn(dailySignVo);
        return new Resp(200, "签到成功");
    }

}
