# 批量插入数据
DROP PROCEDURE IF EXISTS `fengshiqing`.fsqBatchInsert;
DELIMITER $$
CREATE PROCEDURE `fengshiqing`.fsqBatchInsert(IN max_num INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    START TRANSACTION; -- 避免每次都生成一个新的事务，浪费性能
    WHILE(i < max_num)
        DO
            INSERT INTO `fengshiqing`.`t_product` (
                `name`, `code`, `price`
            ) VALUES (
                CONCAT('冯仕清', ROUND(RAND()*1000, 0)), CONCAT('fengshiqing', ROUND(RAND()*1000, 0)), ROUND(RAND()*1000, 0)
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
CALL fsqBatchInsert(10);

