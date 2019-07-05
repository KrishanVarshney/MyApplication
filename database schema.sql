/*
SQLyog Enterprise - MySQL GUI v8.02 RC
MySQL - 5.5.16 : Database - b253
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`b253` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `b253`;

/*Table structure for table `admindata` */

DROP TABLE IF EXISTS `admindata`;

CREATE TABLE `admindata` (
  `name` varchar(100) DEFAULT NULL,
  `contact` int(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `admindata` */

insert  into `admindata`(`name`,`contact`,`email`) values ('admin',2147483647,'admin@gmail.com');

/*Table structure for table `logindata` */

DROP TABLE IF EXISTS `logindata`;

CREATE TABLE `logindata` (
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `logindata` */

insert  into `logindata`(`email`,`password`,`usertype`) values ('admin@gmail.com','admin','admin'),('student@gmail.com','student','student'),('student2@gmail.com','student2','student');

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `nid` int(100) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `nfor` varchar(100) DEFAULT NULL,
  `nyear` varchar(100) DEFAULT NULL,
  `nbranch` varchar(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `ndate` int(100) DEFAULT NULL,
  `nby` varchar(100) DEFAULT NULL,
  UNIQUE KEY `sno` (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `notice` */

insert  into `notice`(`nid`,`title`,`nfor`,`nyear`,`nbranch`,`content`,`ndate`,`nby`) values (1,'xyz','all','all','all','qwerty',1554060675,'admin@gmail.com'),(2,'lol','all','1','cse','rofl',1555046509,'admin@gmail.com');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `name` varchar(50) DEFAULT NULL,
  `branch` varchar(50) DEFAULT NULL,
  `year` varchar(50) DEFAULT NULL,
  `roll` varchar(50) DEFAULT NULL,
  `contact` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`name`,`branch`,`year`,`roll`,`contact`,`email`) values ('student','cse','4','1',2147483647,'student@gmail.com'),('student2','cse','3','1008',2147483647,'student2@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
