# 新建 t_user 表
CREATE TABLE t_user (
    `id`                 UNSIGNED BIGINT    AUTO_INCREMENT    PRIMARY KEY            COMMENT '自增主键ID',
    `userId`             BIGINT(20)         NOT NULL                                 COMMENT '用户ID',
    `username`           VARCHAR(32)        NOT NULL                                 COMMENT '用户名称',
    `password`           VARCHAR(64)        NOT NULL                                 COMMENT '用户登录密码',
    `gender`             TINYINT            NOT NULL    DEFAULT 0                    COMMENT '性别：0保密、1男性、2女性',
    `telephone`          VARCHAR(20)            NULL                                 COMMENT '手机号码',
    `email`              VARCHAR(32)        NOT NULL                                 COMMENT '邮箱',
    `last_login_time`    DATETIME               NULL                                 COMMENT '上一次登录时间',
    `create_time`        DATETIME               NULL                                 COMMENT '创建时间',
    `update_time`        DATETIME           NOT NULL    DEFAULT CURRENT_TIMESTAMP    COMMENT '修改时间',
    `create_user`        INT                    NULL                                 COMMENT '创建人',
    `update_user`        INT                    NULL                                 COMMENT '修改人'
) COMMENT '用户表';


CREATE TABLE IF NOT EXISTS `t_product` (
    `id`     BIGINT         NOT NULL        AUTO_INCREMENT COMMENT '自增主键ID',
    `name`   VARCHAR(32)    DEFAULT NULL    COMMENT '名称',
    `code`   VARCHAR(32)    DEFAULT NULL    COMMENT '编码',
    `price`  INT            DEFAULT NULL    COMMENT '价格',
    PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;


# 将 products 表的数据复制到 t_product 表
INSERT INTO t_product  (id, `name`, `code`, price) SELECT  id, `name`, `code`, price FROM products;

