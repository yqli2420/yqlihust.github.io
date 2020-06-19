/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.26 : Database - db_diary
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_diary` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_diary`;

/*Table structure for table `t_diary` */

DROP TABLE IF EXISTS `t_diary`;

CREATE TABLE `t_diary` (
  `diaryId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `title` varchar(60) DEFAULT NULL,
  `content` text,
  `typeId` int(11) DEFAULT NULL,
  `releaseDate` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`diaryId`),
  KEY `typeId` (`typeId`),
  CONSTRAINT `t_diary_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `t_diarytype` (`diaryTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `t_diary` */

/*Table structure for table `t_diarytype` */

DROP TABLE IF EXISTS `t_diarytype`;

CREATE TABLE `t_diarytype` (
  `diaryTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `typeName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`diaryTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `t_diarytype` */


/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `nickName` varchar(20) DEFAULT NULL,
  `imageName` varchar(40) DEFAULT NULL,
  `mood` varchar(200) DEFAULT NULL,
  `joinDate` varchar(30) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
