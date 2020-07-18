DELIMITER $$



DROP PROCEDURE IF EXISTS `pro_TableCreate`$$

CREATE  PROCEDURE `pro_TableCreate`(
)
BEGIN
  DECLARE i INT;
  DECLARE table_name VARCHAR(20);
  SET i = 0;

  WHILE i<25 DO
  #为了使表名成为xxx00这样的格式加的条件判断
  IF i<10 THEN
    SET table_name = CONCAT('table_one_',i);
  ELSE
    SET table_name = CONCAT('table_one_',i);
  END IF;

  SET @csql = CONCAT(
      'CREATE TABLE ',table_name,'(
id bigint  NOT NULL auto_increment COMMENT"主键ID",
phone varchar(20) comment"注释",
back_one varchar(50) comment"注释",
back_two varchar(50) comment"注释",
back_three varchar(50) comment"注释",

PRIMARY KEY(id)
)ENGINE=Innodb default charset=utf8;'
    );

  PREPARE create_stmt FROM @csql;
  EXECUTE create_stmt;
  SET i = i+1;
  END WHILE;

END$$
DELIMITER ;
call pro_TableCreate();
#执行
