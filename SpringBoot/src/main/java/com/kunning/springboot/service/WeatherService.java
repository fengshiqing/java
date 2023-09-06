/*
 * Copyright (c) 2020. fengshiqing 冯仕清. All right reserved.
 */

package com.kunning.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 功能描述：天气预报服务。
 * 数据来源于：中国气象局(http://www.cma.gov.cn/)的中国天气网(http://www.weather.com.cn/)
 *
 * @author fengshiqing
 * @since 2020-11-29 19:00:00
 */
@Service
public class WeatherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherService.class);

    private final RestTemplate restTemplate;

    /**
     * 构造器
     */
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getWeatherInfo() {
        LOGGER.info("【getWeatherInfo】【start】");

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        HttpEntity entity = new HttpEntity(headers);
        restTemplate.postForObject("", entity, String.class);
    }

}
