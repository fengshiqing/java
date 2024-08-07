# 新建数据库， DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci
CREATE DATABASE IF NOT EXISTS vonsiking; -- 默认就是 utf8mb4 编码，utf8mb4_0900_ai_ci 排序规则


# 建表原则：
# 使用满足需求的最小长度/大小的字段
# 能用数字类型，就用数字类型，能用 UNSIGNED 就用 UNSIGNED，参考：https://deepinout.com/mysql/mysql-questions/t_what-is-unsigned-in-mysql.html
# 每个字段都尽量 设置 NOT NULL
# 每个字段都尽量 设置 DEFAULT 值
# 更新时间 使用 ON UPDATE NOW() 自动更新


# 新建 t_user 表
# DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
    `id`                       BIGINT UNSIGNED                 AUTO_INCREMENT    PRIMARY KEY       COMMENT '自增主键ID',
    `userId`                   BIGINT UNSIGNED     NOT NULL                                        COMMENT '用户ID',
    `password`                 VARCHAR(128)        NOT NULL                                        COMMENT '用户登录密码',
    `username`                 VARCHAR(32)         NOT NULL                                        COMMENT '用户名',
    `gender`                   TINYINT             NOT NULL    DEFAULT 0                           COMMENT '性别：0保密、1男性、2女性',
    `age`                      TINYINT                 NULL    DEFAULT NULL                        COMMENT '年龄',
    `personalized_signature`   VARCHAR(256)            NULL    DEFAULT NULL                        COMMENT '个性签名',
    `telephone`                VARCHAR(20)             NULL    DEFAULT NULL                        COMMENT '手机号码',
    `email`                    VARCHAR(32)             NULL    DEFAULT NULL                        COMMENT '邮箱',
    `last_login_time`          DATETIME                NULL    DEFAULT NULL                        COMMENT '上一次登录时间',

    `create_time`              DATETIME            NOT NULL    DEFAULT NOW()                       COMMENT '创建时间',
    `update_time`              DATETIME            NOT NULL    DEFAULT NOW()    ON UPDATE NOW()    COMMENT '修改时间',
    `del_flag`                 TINYINT             NOT NULL    DEFAULT 0                           COMMENT '删除标记：0未删除、1已删除',
    UNIQUE INDEX t_user_index_userId (userId)
) COMMENT '用户表';

# 新建 t_product 表
CREATE TABLE IF NOT EXISTS `t_product` (
    `id`             BIGINT UNSIGNED                AUTO_INCREMENT    PRIMARY KEY       COMMENT '自增主键ID',
    `name`           VARCHAR(32)        NOT NULL                                        COMMENT '产品名称',
    `code`           VARCHAR(32)        NOT NULL                                        COMMENT '产品编码',
    `description`    VARCHAR(128)           NULL    DEFAULT NULL                        COMMENT '产品描述',
    `price`          DECIMAL(16,4)          NULL    DEFAULT NULL                        COMMENT '产品价格',

    `create_user`    BIGINT UNSIGNED        NULL                                        COMMENT '创建人',
    `create_time`    DATETIME           NOT NULL    DEFAULT NOW()                       COMMENT '创建时间',
    `update_user`    BIGINT UNSIGNED    NOT NULL                                        COMMENT '修改人',
    `update_time`    DATETIME           NOT NULL    DEFAULT NOW()    ON UPDATE NOW()    COMMENT '修改时间',
    `del_flag`       TINYINT            NOT NULL    DEFAULT 0                           COMMENT '删除标记：0未删除、1已删除'
) COMMENT '产品表';


# 将 products 表的数据复制到 t_product 表
INSERT INTO t_product  (id, `name`, `code`, price) SELECT  id, `name`, `code`, price FROM products;

