/*
 * Copyright (c) 2024. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloud.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "日常签到的实体类")
public class DailySignVo {

    /**
     * 这里使用 NotNull 的话，空字符串是不会被检验住的，就得靠@Size来校验，但是我觉得应该被这个校验住，因此不使用NotNull，而使用NotBlank
     * 校验用的@Size如果加了 min属性，错误提示会有两个，空字符串会被 @NotBlank 和 @Size 都校验到，因此去掉了 min属性
     */
    @Schema(description = "用户ID")
    @NotBlank(message = "{biz.DailySignVo.userId.null}")
    @Size(max = 64, message = "{biz.DailySignVo.userId.error}")
    private String userId;


    @Schema(description = "签到日期，取值范围[1, 31]")
    @NotNull(message = "{biz.DailySignVo.dayOfMonth.null}")
//    @Digits(integer = 2, fraction = 0, message = "{biz.DailySignVo.dayOfMonth.error}") //  验证数字是否符合指定格式
    @Range(min = 1, max = 31, message = "{biz.DailySignVo.dayOfMonth.error}") // 验证数字是否在指定范围内
    private int dayOfMonth;


    @Schema(description = "签到月份，取值范围[1, 999999]，示例：202309，202310。不填时为当前月份，填之前的月份就表明补签之前的月份的某一天的签到")
    @NotNull(message = "{biz.DailySignVo.month.null}")
    @Range(min = 1, max = 999999, message = "{biz.DailySignVo.month.error}")
    private int month;


    @Schema(description = "签到月份，取值范围[1, 999999]，示例：202309，202310。不填时为当前月份，填之前的月份就表明补签之前的月份的某一天的签到")
    @NotNull(message = "date必填")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}
