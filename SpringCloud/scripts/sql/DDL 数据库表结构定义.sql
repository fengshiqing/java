# 新建数据库， DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci
CREATE DATABASE IF NOT EXISTS vonsiking; -- 默认就是 utf8mb4 编码，utf8mb4_0900_ai_ci 排序规则


# 建表原则：
# 使用满足需求的最小长度/大小的字段
# 能用数字类型，就用数字类型，能用 UNSIGNED 就用 UNSIGNED，参考：https://deepinout.com/mysql/mysql-questions/t_what-is-unsigned-in-mysql.html
# 主键最好不要用 BIGINT UNSIGNED，最好用BIGINT，因为 BIGINT 对应 java 中的基本类型long，性能高，而无符号的BIGINT只能使用Biginteger类型来处理，性能略低
# 每个字段都尽量 设置 NOT NULL
# 每个字段都尽量 设置 DEFAULT 值
# 更新时间 使用 ON UPDATE NOW() 自动更新


# 新建 t_user 表
# DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
    `id`                       BIGINT              NOT NULL    AUTO_INCREMENT    PRIMARY KEY       COMMENT '自增主键ID',
    `user_id`                  VARCHAR(20)         NOT NULL                                        COMMENT '用户ID',
    `password`                 VARCHAR(128)        NOT NULL                                        COMMENT '用户登录密码',
    `user_name`                VARCHAR(32)         NOT NULL                                        COMMENT '用户名',
    `gender`                   TINYINT             NOT NULL    DEFAULT 0                           COMMENT '性别：0保密、1男性、2女性',
    `age`                      TINYINT                 NULL    DEFAULT NULL                        COMMENT '年龄',
    `personalized_signature`   VARCHAR(256)            NULL    DEFAULT NULL                        COMMENT '个性签名',
    `telephone`                VARCHAR(20)             NULL    DEFAULT NULL                        COMMENT '手机号码',
    `email`                    VARCHAR(32)             NULL    DEFAULT NULL                        COMMENT '邮箱',
    `last_login_time`          DATETIME                NULL    DEFAULT NULL                        COMMENT '上一次登录时间',

    `create_time`              DATETIME            NOT NULL    DEFAULT NOW()                       COMMENT '创建时间',
    `update_time`              DATETIME            NOT NULL    DEFAULT NOW()    ON UPDATE NOW()    COMMENT '修改时间',
    `del_flag`                 BIGINT              NOT NULL    DEFAULT 0                           COMMENT '删除标记：0未删除、删除后置为id',
    UNIQUE INDEX t_user_index_userId (user_id, del_flag)
) COMMENT '用户表';



# 新建 t_user_daily_sign_in 表
CREATE TABLE IF NOT EXISTS `t_user_daily_sign_in` (
    `user_id`           VARCHAR(20)    NOT NULL                                        COMMENT '用户ID',
    `month`             int            NOT NULL                                        COMMENT '签到月份，例如：202309，202310',
    `detail`            BIT(31)        NOT NULL                                        COMMENT '当月的签到记录详情',
    `continuous_num`    int            NOT NULL                                        COMMENT '当月最大连续签到次数',
    `total_num`         int            NOT NULL                                        COMMENT '当月签到总次数',
    `create_time`       DATETIME       NOT NULL    DEFAULT NOW()                       COMMENT '创建时间',
    `update_time`       DATETIME       NOT NULL    DEFAULT NOW()    ON UPDATE NOW()    COMMENT '修改时间',
    PRIMARY KEY (`user_id`, `month`)
) COMMENT '用户签到表';



# 新建 t_product 表
CREATE TABLE IF NOT EXISTS `t_product` (
    `id`              BIGINT           NOT NULL    AUTO_INCREMENT    PRIMARY KEY       COMMENT '自增主键ID',
    `product_code`    VARCHAR(32)      NOT NULL                                        COMMENT '商品编码',
    `product_name`    VARCHAR(32)      NOT NULL                                        COMMENT '商品名称',
    `description`     VARCHAR(1024)        NULL    DEFAULT NULL                        COMMENT '商品描述',
    `original_price`  DECIMAL(16,4)    NOT NULL                                        COMMENT '商品价格（原价），整数位12位（千亿级别），小数位4位，总共16位有效数据',
    `discount_price`  DECIMAL(16,4)    NOT NULL                                        COMMENT '商品价格（打折后的折扣价）',

    `create_user`     VARCHAR(20)      NOT NULL                                        COMMENT '创建人',
    `create_time`     DATETIME         NOT NULL    DEFAULT NOW()                       COMMENT '创建时间',
    `update_user`     VARCHAR(20)      NOT NULL                                        COMMENT '修改人',
    `update_time`     DATETIME         NOT NULL    DEFAULT NOW()    ON UPDATE NOW()    COMMENT '修改时间',
    `del_flag`        BIGINT           NOT NULL    DEFAULT 0                           COMMENT '删除标记：0未删除、1已删除',
    UNIQUE INDEX t_product_index_productCode (product_code, del_flag)
) COMMENT '商品表';


# 新建 t_order 表
CREATE TABLE IF NOT EXISTS `t_order` (
    `order_id`          VARCHAR(32)      NOT NULL                      PRIMARY KEY       COMMENT '主键ID，订单号，分布式ID',
    `user_id`           VARCHAR(20)      NOT NULL                                        COMMENT '用户ID',
    `product_code`      VARCHAR(32)      NOT NULL                                        COMMENT '商品编码',

    `order_price`       DECIMAL(16,4)    NOT NULL                                        COMMENT '订单金额',

    `receiver`          VARCHAR(1024)    NOT NULL                                        COMMENT '收件人',
    `receiver_phone`    VARCHAR(20)      NOT NULL                                        COMMENT '收件人电话',
    `receiver_address`  VARCHAR(300)     NOT NULL                                        COMMENT '收件人地址',

    `create_user`       VARCHAR(20)      NOT NULL                                        COMMENT '创建人/购买人',
    `create_time`       DATETIME         NOT NULL    DEFAULT NOW()                       COMMENT '创建时间/购买时间',
    `update_user`       VARCHAR(20)      NOT NULL                                        COMMENT '修改人',
    `update_time`       DATETIME         NOT NULL    DEFAULT NOW()    ON UPDATE NOW()    COMMENT '修改时间',
    `del_flag`          BIGINT           NOT NULL    DEFAULT 0                           COMMENT '删除标记：0未删除、1已删除',
    UNIQUE INDEX t_product_index_userId_productCode (user_id, product_code, del_flag), -- 用于查询 用户购买了哪些商品
    UNIQUE INDEX t_product_index_productCode_userId (product_code, user_id, del_flag)  -- 用于查询 商品被哪些用户购买了
) COMMENT '订单表/用户商品关联关系表（用户购买了哪些商品，商品被哪些用户购买了）';



# 新建 t_user_flow 表
CREATE TABLE IF NOT EXISTS `t_user_flow` (
    `user_id`          VARCHAR(20)      NOT NULL                                        COMMENT '用户ID',
    `flow_id`          VARCHAR(64)      NOT NULL                                        COMMENT '流程ID，对应 ACT_HI_ACTINST 表的 PROC_INST_ID_ 字段',
    `flow_type`        VARCHAR(32)      NOT NULL                                        COMMENT '流程类型',
    `current_approver` VARCHAR(32)      NOT NULL                                        COMMENT '当前审批人',
    `remark`           VARCHAR(1024)    NOT NULL                                        COMMENT '申请原因/备注',

    `create_user`      VARCHAR(20)      NOT NULL                                        COMMENT '创建人',
    `create_time`      DATETIME         NOT NULL    DEFAULT NOW()                       COMMENT '创建时间',
    `update_user`      VARCHAR(20)      NOT NULL                                        COMMENT '修改人',
    `update_time`      DATETIME         NOT NULL    DEFAULT NOW()    ON UPDATE NOW()    COMMENT '修改时间',
    `del_flag`         BIGINT           NOT NULL    DEFAULT 0                           COMMENT '删除标记：0未删除、1已删除',
    PRIMARY KEY (user_id, flow_id)
) COMMENT '用户流程表，各种流程：请假流程、加班流程、费用报销流程等等';

# 新建 t_user_flow_detail 表
CREATE TABLE IF NOT EXISTS `t_user_flow_detail` (
    `user_id`        VARCHAR(20)        NOT NULL                                        COMMENT '用户ID',
    `flow_id`        VARCHAR(64)        NOT NULL                                        COMMENT '流程ID，对应 ACT_HI_ACTINST 表的 PROC_INST_ID_ 字段',
    `detail_info`    VARCHAR(1024)      NOT NULL                                        COMMENT '详细信息',

    `create_user`    VARCHAR(20)        NOT NULL                                        COMMENT '创建人',
    `create_time`    DATETIME           NOT NULL    DEFAULT NOW()                       COMMENT '创建时间',
    `update_user`    VARCHAR(20)        NOT NULL                                        COMMENT '修改人',
    `update_time`    DATETIME           NOT NULL    DEFAULT NOW()    ON UPDATE NOW()    COMMENT '修改时间',
    `del_flag`       BIGINT             NOT NULL    DEFAULT 0                           COMMENT '删除标记：0未删除、1已删除',
    PRIMARY KEY (user_id, flow_id)
) COMMENT '用户流程的详细信息，请假流程中的请假时间、加班流程中的加班时间、费用报销流程中的费用信息等等';



# 新建 t_score 表
CREATE TABLE IF NOT EXISTS `t_score` (
    `grade`    INT            NOT NULL    COMMENT '年级',
    `class`    VARCHAR(10)    NOT NULL    COMMENT '班级',
    `score`    INT            NOT NULL    COMMENT '分数'
) COMMENT '分数表（年级根据、班级划分）';

INSERT INTO t_score  VALUES
        (1, 'A', 90),
        (1, 'B', 87),
        (2, 'A', 93),
        (1, 'C', 100);

SELECT
	`grade`,
	SUM( IF(class='A', score, NULL)) AS 'classA',
	SUM( IF(class='B', score, NULL)) AS 'classB',
	SUM( IF(class='C', score, NULL)) AS 'classC' -- 班级增加的话，在这里新增一行就行
  FROM t_score
 WHERE 1 = 1
 GROUP BY `grade`
;



# 排行榜操作历史表，可以使用 此表的数据来统计出 最终的排行榜： select sum(quantity), biz_id  FROM t_ranking  GROUP BY  biz_id; -- 这样会比较慢，因此直接再新建一张结果表
CREATE TABLE IF NOT EXISTS t_ranking_history (
    id           BIGINT         PRIMARY KEY  AUTO_INCREMENT  COMMENT '自增主键',
    rank_type    VARCHAR(20)    NOT NULL  DEFAULT ''      COMMENT '榜单类型，比如：like(点赞)、collect(收藏)、transmit(转发)、comment(评论)、score(得分)',
    biz_id       VARCHAR(20)    NOT NULL  DEFAULT ''      COMMENT '排行榜中的业务对象的ID，例如 帖子的ID、文章的ID',
    quantity     INT            NOT NULL  DEFAULT 0       COMMENT '数量变化 (点赞+1, 取消点赞-1, 评分+10)',
    opt_time     DATETIME(3)    NOT NULL  DEFAULT NOW(3)  COMMENT '操作时间',
#     这里本来想使用 联合主键的，deepseek了下 联合主键的 优缺点：
#     优点
#         查询性能：当查询条件完全匹配联合主键的所有字段时，性能非常好（等同于单列主键）
#         天然索引：联合主键本身就是一个复合索引，可以避免额外创建索引
#         数据唯一性：确保多个字段组合的唯一性
#     缺点
#         写入性能：比单列自增主键稍差，特别是当联合主键包含非顺序字段时
#         索引膨胀：联合主键会占用更多存储空间（InnoDB的二级索引会包含主键值）————这个要尤其注意，索引多的情况下，要避免使用联合主键。
#         页分裂风险：非顺序的主键可能导致更多的页分裂操作————这一点和 第一点 是一样的问题，也分裂就会导致写入速度变慢。
    unique key index_key_union(rank_type, biz_id, quantity, opt_time) -- 创建主键(多字段联合主键)
) COMMENT='排行榜操作历史表';



# 排行榜结果表，直接可以查询出某个排行榜的清单列表： select biz_id, total_quantity  FROM t_ranking  WHERE rank_type='like'  ORDER BY  total_quantity DESC; -- 这样会比较慢，因此直接再新建一张结果表
CREATE TABLE IF NOT EXISTS t_ranking_Result (
    id                BIGINT         PRIMARY KEY             AUTO_INCREMENT       COMMENT '自增主键',
    rank_type         VARCHAR(20)    NOT NULL  DEFAULT ''                         COMMENT '榜单类型，比如：like(点赞)、collect(收藏)、transmit(转发)、comment(评论)',
    biz_id            VARCHAR(20)    NOT NULL  DEFAULT ''                         COMMENT '排行榜中的业务对象的ID，例如 帖子的ID、文章的ID',
    total_quantity    INT            NOT NULL  DEFAULT 0                          COMMENT '总数量 (点赞数, 评分)',
    last_update_time  DATETIME(3)    NOT NULL  DEFAULT NOW(3)  ON UPDATE  NOW(3)  COMMENT '操作时间',
    unique key unique_key_union(rank_type, biz_id) -- 创建主键(多字段联合主键)
) COMMENT='排行榜结果表';


