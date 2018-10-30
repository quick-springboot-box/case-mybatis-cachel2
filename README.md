## create mysql db, & table
-   create database `box-mybatis` default character set utf8 collate utf8_general_ci;

- ddl
  CREATE TABLE `orders` (
        `id` INT(11) NOT NULL,
        `amount` DECIMAL(10,2) NULL DEFAULT NULL,
        `desc` VARCHAR(100) NULL DEFAULT NULL,
        `createdTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
        PRIMARY KEY (`id`, `createdTime`),

        INDEX `Index_unique` (`id`, `amount`)
)
COMMENT='订单'
COLLATE='utf8_general_ci'
/*!50100 PARTITION BY RANGE (TO_DAYS(createdTime))
(PARTITION p1 VALUES LESS THAN (737362) ENGINE = InnoDB,
 PARTITION p2 VALUES LESS THAN (737363) ENGINE = InnoDB,
 PARTITION p3 VALUES LESS THAN (737364) ENGINE = InnoDB,
 PARTITION p4 VALUES LESS THAN (737365) ENGINE = InnoDB,
 PARTITION p5 VALUES LESS THAN (737366) ENGINE = InnoDB)  */;

- dml
INSERT INTO `box-mybatis`.`orders` (`id`, `amount`, `desc`, `createdTime`) VALUES ('1', '10.24', 'cachel2', '2018-10-30 11:24:23');


## access api 
https://github.com/quick-springboot-box/case-mybatis-cachel2