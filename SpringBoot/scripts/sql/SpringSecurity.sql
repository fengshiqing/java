# Spring Security 用到的表，用户表，权限表，角色表
# 来源于 https://blog.csdn.net/I_am_Hutengfei/article/details/100561564

create table sys_user (
    id                       int                   auto_increment        primary key,
    user_Id                  bigint(20)            not null comment '用户ID',
    user_name                varchar(32)           not null comment '用户名',
    password                 varchar(64)           null comment '用户密码',
    account_Non_Expired      tinyint(1) default 1  null comment '账号是否过期。默认为1（没有过期）',
    account_Non_Locked       tinyint(1) default 1  null comment '账号是否锁定。默认为1（没有锁定）',
    credentials_Non_Expired  tinyint(1) default 1  null comment '证书（密码）是否过期。默认为1（没有过期）',
    enabled                  tinyint(1) default 1  null comment '账号是否可用。默认为1（可用）',
    last_login_time          datetime              null comment '上一次登录时间',
    create_time              datetime              null comment '创建时间',
    update_time              datetime              null comment '修改时间',
    create_user              int                   null comment '创建人',
    update_user              int                   null comment '修改人'
) comment '用户表';

create table sys_role (
    id               int auto_increment comment '主键id'
        primary key,
    role_code        varchar(32) null comment '角色code',
    role_name        varchar(32) null comment '角色名',
    role_description varchar(64) null comment '角色说明'
) comment '用户角色表';

create table sys_user_role_relation (
    id      int auto_increment comment '主键id'
        primary key,
    user_id int null comment '用户id',
    role_id int null comment '角色id'
) comment '用户-角色关联关系表，一个用户可以有多种角色/一个角色下可以有多个用户';

create table sys_permission (
    id              int auto_increment comment '主键id'
        primary key,
    permission_code        varchar(32) null comment '权限code',
    permission_name        varchar(32) null comment '权限名',
    permission_description varchar(64) null comment '权限说明'
) comment '权限表';

create table sys_role_permission_relation (
    id            int auto_increment comment '主键id'
        primary key,
    role_id       int null comment '角色id',
    permission_id int null comment '权限id'
) comment '角色-权限关联关系表';

create table sys_request_path (
    id          int auto_increment comment '主键id'
        primary key,
    url         varchar(64)  not null comment '请求路径',
    description varchar(128) null comment '路径描述'
) comment '请求路径';

create table sys_request_path_permission_relation (
    id            int null comment '主键id',
    url_id        int null comment '请求路径id',
    permission_id int null comment '权限id'
) comment '路径-权限关联表';



# 插入数据

-- 用户
INSERT INTO sys_user (id, user_Id, user_name, password, last_login_time, enabled, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, create_time, update_time, create_user, update_user
    ) VALUES (
          1, 'user1', '用户1', '$2a$10$47lsFAUlWixWG17Ca3M/r.EPJVIb7Tv26ZaxhzqN65nXVcAhHQM4i', '2019-09-04 20:25:36', 1, 1, 1, 1, '2019-08-29 06:28:36', '2019-09-04 20:25:36', 1, 1);
INSERT INTO sys_user (id, user_Id, user_name, password, last_login_time, enabled, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, create_time, update_time, create_user, update_user
    ) VALUES (
          2, 'user2', '用户2', '$2a$10$uSLAeON6HWrPbPCtyqPRj.hvZfeM.tiVDZm24/gRqm4opVze1cVvC', '2019-09-05 00:07:12', 1, 1, 1, 1, '2019-08-29 06:29:24', '2019-09-05 00:07:12', 1, 2);
-- 角色
INSERT INTO sys_role (id, role_code, role_name, role_description) VALUES (1, 'admin', '管理员', '管理员，拥有所有权限');
INSERT INTO sys_role (id, role_code, role_name, role_description) VALUES (2, 'user', '普通用户', '普通用户，拥有部分权限');
-- 权限
INSERT INTO sys_permission (id, permission_code, permission_name, permission_description) VALUES (1, 'create_user', '创建用户', '创建用户');
INSERT INTO sys_permission (id, permission_code, permission_name, permission_description) VALUES (2, 'query_user', '查看用户', '查看用户');
INSERT INTO sys_permission (id, permission_code, permission_name, permission_description) VALUES (3, 'delete_user', '删除用户', '删除用户');
INSERT INTO sys_permission (id, permission_code, permission_name, permission_description) VALUES (4, 'modify_user', '修改用户', '修改用户');
-- 请求路径
INSERT INTO sys_request_path (id, url, description) VALUES (1, '/getUser', '查询用户');
INSERT INTO sys_request_path (id, url, description) VALUES (2, '/hello', '查询用户');
INSERT INTO sys_request_path (id, url, description) VALUES (3, '/signin', '查询用户');
INSERT INTO sys_request_path (id, url, description) VALUES (4, '/signup', '创建用户');
INSERT INTO sys_request_path (id, url, description) VALUES (5, '/signout', '查询用户');
-- 用户角色关联关系
INSERT INTO sys_user_role_relation (id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO sys_user_role_relation (id, user_id, role_id) VALUES (2, 2, 2);
-- 角色权限关联关系
INSERT INTO sys_role_permission_relation (id, role_id, permission_id) VALUES (1, 1, 1);
INSERT INTO sys_role_permission_relation (id, role_id, permission_id) VALUES (2, 1, 2);
INSERT INTO sys_role_permission_relation (id, role_id, permission_id) VALUES (3, 1, 3);
INSERT INTO sys_role_permission_relation (id, role_id, permission_id) VALUES (4, 1, 4);
INSERT INTO sys_role_permission_relation (id, role_id, permission_id) VALUES (5, 2, 1);
INSERT INTO sys_role_permission_relation (id, role_id, permission_id) VALUES (6, 2, 2);
-- 请求路径权限关联关系
INSERT INTO sys_request_path_permission_relation (id, url_id, permission_id) VALUES (null, 1, 2);
