# 存储过程

# 创建一个用于 批量插入数据 的存储过程
DROP PROCEDURE IF EXISTS `vonsiking`.batchInsert;
DELIMITER $$
CREATE PROCEDURE `vonsiking`.batchInsert(IN max_num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    START TRANSACTION; -- 避免每次都生成一个新的事务，浪费性能
    WHILE(i < max_num)
        DO
            INSERT INTO `vonsiking`.`t_user` (
                `user_id`, `user_name`, `password`, `personalized_signature`
            ) VALUES (
		        UUID_SHORT(),
                CONCAT('冯仕清', ROUND(RAND()*1000, 0)),
                CONCAT('password', ROUND(RAND()*1000, 0)),
                CONCAT('fengshiqing', ROUND(RAND()*1000, 0))
            );
            SET i = i + 1;
    END WHILE;
    COMMIT;
END $$
DELIMITER $$



# 批量插入数据
DROP PROCEDURE IF EXISTS `vonsiking`.batchInsert;
DELIMITER $$
CREATE PROCEDURE `vonsiking`.batchInsert(IN max_num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    START TRANSACTION; -- 避免每次都生成一个新的事务，浪费性能
    WHILE(i < max_num)
        DO
            INSERT INTO `vonsiking`.`t_product` (
                `product_code`, `product_name`, `description`, `original_price`, `discount_price`, `create_user`, `update_user`
            ) VALUES (
                CONCAT('fengshiqing', ROUND(RAND()*1000, 0)),
                CONCAT('冯仕清', ROUND(RAND()*1000, 0)),
                CONCAT('描述信息', ROUND(RAND()*1000, 0)),
                ROUND(RAND()*1000, 0),
                ROUND(RAND()*1000, 0),
                ROUND(RAND()*100000, 0),
                ROUND(RAND()*100000, 0)
            );
            SET i = i + 1;
    END WHILE;
    COMMIT;
END $$
DELIMITER $$



# t_product 表。4个字段，插入了100w数据，花费了
# 执行耗时   : 57.409 sec
# 传送时间   : 0.001 sec
# 总耗时      : 57.411 sec
CALL batchInsert(10);

