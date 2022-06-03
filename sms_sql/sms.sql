/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 8.0.21 : Database - sms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `sms`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `gid` int NOT NULL AUTO_INCREMENT COMMENT '商品编号，自动生成',
  `gname` varchar(20) NOT NULL COMMENT '商品名称，唯一约束(unique)',
  `gprice` double(18,2) NOT NULL COMMENT '商品价格',
  `gnum` int NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`gid`),
  UNIQUE KEY `gname` (`gname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goods` */

/*Table structure for table `gsales` */

DROP TABLE IF EXISTS `gsales`;

CREATE TABLE `gsales` (
  `gsid` int NOT NULL AUTO_INCREMENT COMMENT '销售编号，自动生成',
  `gid` int NOT NULL COMMENT '商品编号',
  `sid` int NOT NULL COMMENT '营业员编号',
  `sdate` date NOT NULL COMMENT '销售日期',
  `snum` int NOT NULL COMMENT '销售数量',
  PRIMARY KEY (`gsid`),
  KEY `gid` (`gid`),
  KEY `sid` (`sid`),
  CONSTRAINT `gsales_ibfk_1` FOREIGN KEY (`gid`) REFERENCES `goods` (`gid`),
  CONSTRAINT `gsales_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `salesman` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `gsales` */

/*Table structure for table `salesman` */

DROP TABLE IF EXISTS `salesman`;

CREATE TABLE `salesman` (
  `sid` int NOT NULL AUTO_INCREMENT COMMENT '营业员编号，自动生成',
  `sname` varchar(10) NOT NULL COMMENT '营业员姓名，用于登录收银，唯一约束',
  `spassword` varchar(20) NOT NULL COMMENT '营业员密码',
  PRIMARY KEY (`sid`),
  UNIQUE KEY `sname` (`sname`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `salesman` */

insert  into `salesman`(`sid`,`sname`,`spassword`) values (1,'admin','123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
