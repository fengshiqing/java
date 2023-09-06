/*
 * Copyright (c) 2023. fengshiqing 冯仕清. All Rights Reserved.
 */

package com.fengshiqing.springcloudgateway;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, Object> respMap = new HashMap<>();

        // 推荐前端发送请求将 token 放在 header
        String token = request.getHeader("token");
        if (StringUtils.hasText(token)) {
            try {
                JWTUitls.getTokenInfo(token);// 验证令牌
                respMap.put("state", true);
                respMap.put("msg", "验证token成功！");
                return true;
            } catch (SignatureVerificationException e) {// 签名匹配异常
                log.error("【无效签名！】");
                respMap.put("msg", "无效签名！");
            } catch (TokenExpiredException e) {
                log.error("【token已经过期！】");
                respMap.put("msg", "token已经过期！");
            } catch (AlgorithmMismatchException e) {
                log.error("【算法异常！】");
                respMap.put("msg", "算法异常！");
            } catch (Exception e) {
                log.error("【无效签名！】");
                respMap.put("msg", "无效签名!");
            }
        } else {
            respMap.put("msg", "没有token签名，或者token为空字符串！");
        }
        respMap.put("state", false);// 设置状态

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(respMap);
        response.setContentType("application/json;charset=utf8;");
        response.getWriter().write(json);
        return false;
    }

}
