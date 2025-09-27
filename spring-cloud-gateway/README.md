# 微服务网关
Spring-Cloud-Gateway 的学习


看这个视频：
https://www.bilibili.com/video/BV1aB4y1P7eh/?spm_id_from=333.337.search-card.all.click&vd_source=f2fb919142ce62e6571426a12817634e
和这个视：
https://www.bilibili.com/video/BV1hd4y1q71b/?spm_id_from=333.788.player.switch&vd_source=f2fb919142ce62e6571426a12817634e&p=9




# Spring Cloud Gateway 整合 Sentinel 限流
从 Sentinel 1.6.0 开始，官方提供了对 Spring Cloud Gateway 的适配支持，支持两种维度的限流：

1. 限流维度
   Route 维度
   基于 Spring Cloud Gateway 配置的路由条目（routeId）进行限流。
   自定义 API 维度
   通过 Sentinel API 自定义分组规则（如聚合多个路由为同一资源）。

2. 依赖配置（Maven）
```
        <dependency>
            <groupId>com.alibaba.cloud</groupId><!--这个是 sentinel 整合 gateway 的依赖-->
            <artifactId>spring-cloud-alibaba-sentinel-gateway</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId><!--这个是 sentinel 本身功能的依赖-->
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>
```


3. 基础配置类示例
   若未使用 Spring Cloud Alibaba Sentinel 自动配置类 SentinelSCGAutoConfiguration.java，需手动注入以下 Bean：

关键说明
版本兼容性

Sentinel 1.6.0+ 支持 Spring Cloud Gateway。

推荐使用 Spring Cloud Alibaba Sentinel 自动配置 SentinelSCGAutoConfiguration.java（简化流程）。

自动配置（推荐）
若项目已引入 spring-cloud-starter-alibaba-sentinel，无需手动编写 GatewayConfiguration，直接通过配置文件启用限流规则即可。

限流规则配置
通过 Sentinel 控制台或代码动态配置规则，例如：

java
// 示例：对 routeId="user-service" 的路由设置 QPS=10 的限流
GatewayFlowRuleManager.loadRules(Collections.singletonList(
new GatewayFlowRule("user-service")
.setCount(10)
.setIntervalSec(1)
));
常见问题
依赖冲突
确保 Sentinel 版本与 Spring Cloud Alibaba 版本匹配（参考官方版本映射）。

限流不生效
检查是否正确注册了 SentinelGatewayFilter 和异常处理器。

如果需要更详细的配置（如自定义异常响应），可扩展 SentinelGatewayBlockExceptionHandler。

