create database seata_order;
use seata_order;
create table t_order (
`id` bigint(20) not null auto_increment primary key,
`user_id` bigint(20) default null comment '用户ID',
`product_id` bigint(20) default null comment '产品ID',
`count` int(11) default null comment '数量',
`money` decimal(18,2) default null comment '金额',
`status` int(1) default null comment '订单状态：0-创建中，1-已完结'
) engine=INNODB auto_increment = 1 default charset = 'utf8';

select * from t_order;

create database seata_storage;
use seata_storage;
create table t_storage (
`id` bigint(20) not null auto_increment primary key,
`product_id` bigint(20) default null comment '产品ID',
`total` int(11) default null comment '总库存',
`used` int(11) default null comment '使用库存',
`residue` int(11) default null comment '剩余库存'
) engine=INNODB auto_increment = 1 default charset = 'utf8';

insert into t_storage(`id`, `product_id`, `total`, `used`, `residue`) values('1', '1', '100' , '0', '100');

select * from t_storage;


create database seata_account;

use seata_account;

create table t_account (
`id` bigint(20) not null auto_increment primary key,
`user_id` bigint(20) default null comment '用户ID',
`total` decimal(18,2) default null comment '总额度',
`used` decimal(18,2) default null comment '使用额度',
`residue` decimal(18,2) default '0' null comment '剩余额度'
) engine=INNODB auto_increment = 1 default charset = 'utf8';

insert into t_account(`id`, `user_id`, `total`, `used`, `residue`) values('1', '1', '10000' , '0', '10000');
select * from t_account;



use seata_account;
-- for AT mode you must to init this sql for you business database. the seata server not need it.
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'increment id',
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME     NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME     NOT NULL COMMENT 'modify datetime',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';

