/*
 * Copyright (c) fengshiqing 冯仕清 2026. All Rights Reserved.
 */

package com.fengshiqing.springai.model.dto;

import lombok.Data;

/**
 * @Title: PasswardDTO
 * @author 冯仕清
 *
 * @Date 2025/3/26 19:02
 * @description: 修改密码DTO
 */

@Data
public class PasswordDTO {
    private Integer id;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
