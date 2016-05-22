/*
SQLyog Ultimate v9.30 
MySQL - 5.1.55-community : Database - wuliao
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wuliao` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `wuliao`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `auth` tinyint(2) DEFAULT NULL COMMENT '0 超级 1 普通管理员 2 用户 ',
  `realname` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `sex` varchar(4) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `age` int(4) DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`,`auth`,`realname`,`sex`,`age`) values (1,'admin','admin',0,'admin','男',12),(2,'operator1','123123',1,'操作员1','男',12),(3,'operator2','111111',1,'操作员2','女',33),(4,'user1','111111',2,'用户1','男',11),(5,'user4','111111',2,'sdf','女',123),(6,'user3','111111',2,'dsf ','男',123);

/*Table structure for table `log` */

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `storehouse_id` bigint(20) DEFAULT NULL,
  `component_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `tag` tinyint(2) DEFAULT NULL COMMENT '1 出库 2 入库 3 修改 4 删除 5 新增',
  `quantity` int(10) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `log` */

insert  into `log`(`id`,`storehouse_id`,`component_name`,`username`,`tag`,`quantity`,`created_at`) values (2,1,'元器件名称1','operator2',3,0,'2014-05-08 13:48:04'),(3,0,'元器件名称2','operator2',5,211,'2014-05-08 13:51:00'),(4,2,'元器件名称2','operator2',3,0,'2014-05-08 13:51:07'),(5,2,'元器件名称2','operator2',2,123,'2014-05-08 13:51:12'),(6,2,'元器件名称2','operator2',1,22,'2014-05-08 13:51:19'),(7,1,'?????1','operator2',4,0,'2014-05-08 13:51:23'),(8,0,'sdfg元器件名称','operator2',5,222,'2014-05-08 13:51:55'),(9,3,'sdfg?????','operator2',4,0,'2014-05-08 13:51:59'),(10,2,'?????2','operator2',4,0,'2014-05-08 13:52:48'),(11,0,'asdfsdf','operator2',5,123,'2014-05-08 13:53:27'),(12,4,'asdfsdf发','operator2',3,0,'2014-05-08 13:53:35'),(13,4,'asdfsdf?','operator2',4,0,'2014-05-08 13:58:47');

/*Table structure for table `storehouse` */

DROP TABLE IF EXISTS `storehouse`;

CREATE TABLE `storehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '料号',
  `component_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '元器件名称',
  `producer` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '生产商',
  `value` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '元器件属性值',
  `agency` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '经销商',
  `price` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '价格',
  `short_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '简称',
  `potting` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '封装',
  `remark` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `quantity` int(10) DEFAULT NULL COMMENT '数量',
  `redline` int(10) DEFAULT NULL COMMENT '警戒线',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `storehouse` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
