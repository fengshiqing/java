# 新建 t_user 表
create table t_user (
    id                   int              auto_increment        primary key,
    userId               bigint(20)       not null comment '用户ID',
    username             varchar(32)      not null comment '用户名',
    password             varchar(64)      null     comment '用户密码',
    last_login_time      datetime         null     comment '上一次登录时间',
    create_time          datetime         null     comment '创建时间',
    update_time          datetime         null     comment '修改时间',
    create_user          int              null     comment '创建人',
    update_user          int              null     comment '修改人'
) comment '用户表';