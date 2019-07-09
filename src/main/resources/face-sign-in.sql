/*
SQLyog Ultimate v8.32 
MySQL - 5.6.43 : Database - face-sign-in
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`face-sign-in` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `face-sign-in`;

/*Table structure for table `activity` */

DROP TABLE IF EXISTS `activity`;

CREATE TABLE `activity` (
  `aid` int(10) NOT NULL AUTO_INCREMENT,
  `aname` varchar(30) DEFAULT NULL,
  `astart_time` datetime DEFAULT NULL,
  `aend_time` datetime DEFAULT NULL,
  `oid` int(10) NOT NULL,
  `gid` int(10) DEFAULT NULL,
  `acharge_man` varchar(30) DEFAULT NULL,
  `acontact` varchar(30) DEFAULT NULL,
  `astatus` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `oid` (`oid`),
  KEY `gid` (`gid`),
  CONSTRAINT `activity_ibfk_2` FOREIGN KEY (`oid`) REFERENCES `organization` (`oid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `activity_ibfk_3` FOREIGN KEY (`gid`) REFERENCES `my_group` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `activity` */

insert  into `activity`(`aid`,`aname`,`astart_time`,`aend_time`,`oid`,`gid`,`acharge_man`,`acontact`,`astatus`) values (25,'程设第一周周二','2019-02-01 12:03:00','2019-02-01 13:34:00',10004,10000,'沈中林','1123456789@qq.com','1'),(32,'打飞鹰铠甲','2019-03-06 12:03:00','2019-03-07 12:03:00',10005,10020,'张超','1234567@qq.com','1'),(33,'摘桃子','2019-03-01 12:03:00','2019-03-08 12:03:00',10005,10021,'熊大','12345@qq.com','1'),(34,'aaa','2019-03-05 20:59:37','2019-03-05 20:59:40',10005,10020,'aaa','aaa','1'),(35,'程设第三周周二','2019-03-07 12:03:00','2019-03-14 12:03:00',10004,10000,'沈中林','isjinhao@163.com','1'),(36,'马克思原理第一周','2019-04-12 12:30:00','2019-04-12 13:00:00',10004,10000,'张三','2214112886@qq.com','1'),(37,'马克思原理第二周','2019-04-12 13:00:00','2019-04-12 13:30:00',10004,10000,'张三','2214112886@qq.com','1'),(38,'16级程设第一周周一','2019-04-04 07:50:00','2019-04-04 08:00:00',10004,10000,'沈中林','isjinhao@163.com','1'),(39,'程设第一周周二','2019-04-04 12:03:00','2019-04-05 12:03:00',10004,10000,'沈中林','12312312312@qq.com','1'),(40,'程设第一周周二','0123-04-12 12:31:00','0123-04-05 12:03:00',10004,10000,'沈中林','12131@11.com','1'),(41,'程设第四周周一','2019-04-04 12:01:00','2019-04-04 12:01:00',10004,10006,'沈中林','121231231@qq.com','1');

/*Table structure for table `data_dict` */

DROP TABLE IF EXISTS `data_dict`;

CREATE TABLE `data_dict` (
  `dtype` varchar(30) DEFAULT NULL,
  `dname` varchar(30) DEFAULT NULL,
  `ddata` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `data_dict` */

/*Table structure for table `my_group` */

DROP TABLE IF EXISTS `my_group`;

CREATE TABLE `my_group` (
  `gid` int(10) NOT NULL AUTO_INCREMENT,
  `gname` varchar(30) DEFAULT NULL,
  `oid` int(10) DEFAULT NULL,
  `gstatus` varchar(2) DEFAULT NULL,
  `gcreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`gid`),
  KEY `group_ibfk_1` (`oid`),
  CONSTRAINT `my_group_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `organization` (`oid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10024 DEFAULT CHARSET=utf8;

/*Data for the table `my_group` */

insert  into `my_group`(`gid`,`gname`,`oid`,`gstatus`,`gcreateTime`) values (10000,'不使用组',10000,NULL,'2019-03-03 10:34:21'),(10002,'2018-2019程序设计实践',10004,NULL,'2019-03-02 17:11:06'),(10003,'2019-2020软件工程',10004,NULL,'2019-03-02 18:12:55'),(10006,'2016-2017程序设计基础',10004,NULL,'2019-03-03 14:27:33'),(10013,'2017-2018数据库原理',10004,NULL,'2019-03-03 14:31:30'),(10020,'铠甲勇士',10005,NULL,'2019-03-03 18:05:15'),(10021,'熊出没',10005,NULL,'2019-03-03 18:12:33');

/*Table structure for table `orgadmins` */

DROP TABLE IF EXISTS `orgadmins`;

CREATE TABLE `orgadmins` (
  `oid` int(10) NOT NULL,
  `uid` varchar(20) NOT NULL,
  PRIMARY KEY (`oid`,`uid`),
  KEY `uid` (`uid`),
  CONSTRAINT `orgadmins_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `organization` (`oid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orgadmins_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orgadmins` */

insert  into `orgadmins`(`oid`,`uid`) values (10004,'160341237');

/*Table structure for table `organization` */

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization` (
  `oid` int(10) NOT NULL AUTO_INCREMENT,
  `opwd` varchar(30) DEFAULT NULL,
  `oemail` varchar(30) NOT NULL,
  `oname` varchar(30) DEFAULT NULL,
  `ocreate_time` datetime DEFAULT NULL,
  `oowner` varchar(30) DEFAULT NULL,
  `oowner_email` varchar(30) DEFAULT NULL,
  `ostatus` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8;

/*Data for the table `organization` */

insert  into `organization`(`oid`,`opwd`,`oemail`,`oname`,`ocreate_time`,`oowner`,`oowner_email`,`ostatus`) values (10000,'0','0','0','0000-00-00 00:00:00','0','0',NULL),(10004,'00000000','isjinhao@163.com','校团委','2019-02-23 19:20:54',NULL,NULL,NULL),(10005,'601220zjh','2214112885@qq.com','大学生就业办','2019-03-03 18:03:57',NULL,NULL,NULL);

/*Table structure for table `signin_record` */

DROP TABLE IF EXISTS `signin_record`;

CREATE TABLE `signin_record` (
  `aid` int(10) DEFAULT NULL,
  `uid` varchar(30) DEFAULT NULL,
  `sin_time` datetime DEFAULT NULL,
  `sout_time` datetime DEFAULT NULL,
  `simg_path` varchar(30) DEFAULT NULL,
  `scheck_type` varchar(2) DEFAULT NULL,
  `sstatus` varchar(2) DEFAULT NULL,
  `confidence` double(20,0) DEFAULT NULL,
  KEY `aid` (`aid`),
  KEY `uid` (`uid`),
  CONSTRAINT `signin_record_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `activity` (`aid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `signin_record_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `signin_record` */

insert  into `signin_record`(`aid`,`uid`,`sin_time`,`sout_time`,`simg_path`,`scheck_type`,`sstatus`,`confidence`) values (32,'160341237','2019-03-03 18:13:58','2019-03-03 18:13:58',NULL,'1','1',100),(25,'160341237','2019-03-05 18:06:09','2019-04-23 14:36:01','160341237.jpg','2','1',NULL),(25,'160341241','2019-04-09 22:53:33','2019-04-09 22:53:40','160341241.jpg','2','1',NULL),(25,'160341233','2019-04-15 19:35:35','2019-04-15 19:35:44','160341233.jpg','2','1',NULL),(35,'160341314','2019-04-23 15:29:50','2019-04-23 15:30:02','160341314.jpg','2','1',NULL),(35,'160341241','2019-04-23 15:54:32','2019-04-23 15:54:32','160341241.jpg','2','1',NULL),(35,'160341301','2019-04-23 16:12:05','2019-04-23 16:12:06','160341301.jpg','2','1',NULL),(35,'160341237','2019-04-23 16:13:36','2019-04-23 16:13:39','160341237.jpg','2','1',NULL),(35,'160341307','2019-04-23 16:23:15','2019-04-23 16:23:15','160341307.jpg','2','1',NULL),(35,'160341320','2019-04-23 16:23:15','2019-04-23 16:23:15','160341320.jpg','2','1',NULL),(39,'160341237','2019-04-23 19:06:18','2019-04-23 19:06:18',NULL,'1','1',100),(40,'160341237','2019-04-23 19:08:50','2019-04-23 19:08:50',NULL,'1','1',100),(37,'160341237','2019-04-23 19:09:33','2019-04-23 19:09:33',NULL,'1','1',100),(36,'160341237','2019-04-23 19:10:13','2019-04-23 19:10:13',NULL,'1','1',100),(38,'160341237','2019-04-23 19:10:36','2019-04-23 19:10:36',NULL,'1','1',100);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` varchar(30) NOT NULL,
  `uname` varchar(30) DEFAULT NULL,
  `upwd` varchar(30) DEFAULT NULL,
  `uemail` varchar(30) NOT NULL,
  `uface_token` varchar(100) DEFAULT NULL,
  `uimg_path` varchar(30) NOT NULL,
  `ustatus` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`uname`,`upwd`,`uemail`,`uface_token`,`uimg_path`,`ustatus`) values ('160314309','何浪','111111','921108347@qq.com','cacbb16ab0e0b9edc4e4155447dce038','9FWW',NULL),('160341109','ghr','818108','ghrgaohaoran@qq.com','d48cfebba0ae0e73b355f0437a6b570f','FHR2',NULL),('160341201','CXK','123456','1257774682@qq.com','b34747d606afd85d7ec0ff414c9f57c4','LCXS',NULL),('160341216','cliu1_16','aaaaaa','919364284@qq.com','1bbda45fd93cc41c7a0cda1c91cb746d','PMCL',NULL),('160341227','wmh','wmh123','1755449302@qq.com','de1e09796917537b383ac72deeaa2774','LTWM',NULL),('160341233','熊宗铭','00000000','876055266@qq.com','9711b446c5b5773bd1c4c628bddb1a9b','K6JN',NULL),('160341237','詹金浩','00000000','isjinhao@163.com','5714ec5b303ae9bf6e423f666fa3854d','160341237.jpg',NULL),('160341241','赵立林','00000000','2096890387@qq.com','54d57d948e8e66a43cbf2b2166f30c13','VC7R',NULL),('160341301','陈聪瑞','111111','crchen_cauc@163.com','a18ab426de647e6872ff4cfb2bcc993f','6WMG',NULL),('160341306','符华奥','160341306','1937635028@qq.com','836c68da1efb1fcf65cc714764286892','W5U6',NULL),('160341307','宫元浩','981216','1342661522@qq.com','7d272e48eb218665b375d8b91574fe2e','SEGV',NULL),('160341314','李奕蓉','123456','504968829@qq.com','1d451884e37b8a746a2b0fa21ebba596','4835',NULL),('160341320','牛蕊滢','111111','1293291503@qq.com','5d1bbeb678dcfdbbcd5416b5ec679e21','GFN8',NULL),('170342227','666','123456','15522523265@163.com','be69ebf9594c15465d00a9ea66537c0e','KH4J',NULL);

/*Table structure for table `user_join_group` */

DROP TABLE IF EXISTS `user_join_group`;

CREATE TABLE `user_join_group` (
  `gid` int(10) NOT NULL,
  `uid` varchar(30) NOT NULL,
  PRIMARY KEY (`gid`,`uid`),
  KEY `uid` (`uid`),
  CONSTRAINT `user_join_group_ibfk_1` FOREIGN KEY (`gid`) REFERENCES `my_group` (`gid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_join_group_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_join_group` */

insert  into `user_join_group`(`gid`,`uid`) values (10002,'160341237'),(10020,'160341237');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
