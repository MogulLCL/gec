/*
Navicat MySQL Data Transfer

Source Server         : 192.168.43.1
Source Server Version : 50505
Source Host           : 192.168.43.1:3306
Source Database       : oa_db

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2021-04-30 16:31:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `announcement_info`
-- ----------------------------
DROP TABLE IF EXISTS `announcement_info`;
CREATE TABLE `announcement_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `createtime` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `announcement_info_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of announcement_info
-- ----------------------------
INSERT INTO `announcement_info` VALUES ('1', '放假通知', '元旦放假时间为1月1号，放假一天', '2020-12-02 11:03:16', '1');
INSERT INTO `announcement_info` VALUES ('2', '111111', '1发热管问问如果认为如果我给个人', '2021-04-27 00:00:00', '1');

-- ----------------------------
-- Table structure for `dept_inf`
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------
INSERT INTO `dept_inf` VALUES ('1', '技术部', '技术部');
INSERT INTO `dept_inf` VALUES ('2', '运营部', '运营部');
INSERT INTO `dept_inf` VALUES ('3', '财务部', '财务部');
INSERT INTO `dept_inf` VALUES ('5', '总公办', '总公办');
INSERT INTO `dept_inf` VALUES ('6', '市场部', '市场部');
INSERT INTO `dept_inf` VALUES ('7', '教研部', '教学研发');
INSERT INTO `dept_inf` VALUES ('9', '销售部', '商品销售');

-- ----------------------------
-- Table structure for `document_inf`
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `filename` varchar(300) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT current_timestamp(),
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DOCUMENT_USER` (`USER_ID`),
  CONSTRAINT `FK_DOCUMENT_USER` FOREIGN KEY (`USER_ID`) REFERENCES `user_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document_inf
-- ----------------------------
INSERT INTO `document_inf` VALUES ('3', '1619571768635_6.jpg', 'test', '2021-04-28 00:00:00', '1');
INSERT INTO `document_inf` VALUES ('6', '1619592979186_201515-158211451517f1.jpg', 'test1', '2021-04-28 00:00:00', '1');
INSERT INTO `document_inf` VALUES ('7', '1619593794194_《Java面试手册》.pdf', '大文件', '2021-04-28 00:00:00', '1');
INSERT INTO `document_inf` VALUES ('8', '1619657767983_abc.jpg', 'aaaaaa', '2021-04-29 00:00:00', '1');
INSERT INTO `document_inf` VALUES ('10', '1619664554258_115722-1584503842bd26.jpg', 'mogultes', '2021-04-29 00:00:00', '1');
INSERT INTO `document_inf` VALUES ('11', '1619665611624_Capture001.png', 'bbbbbbbbbbbb', '2021-04-29 00:00:00', '1');
INSERT INTO `document_inf` VALUES ('13', '1619705594107_Capture001.png', '技术部1', '2021-04-29 00:00:00', '4');
INSERT INTO `document_inf` VALUES ('14', '1619745456918_Capture001.png', '技术部1', '2021-04-30 00:00:00', '1');

-- ----------------------------
-- Table structure for `employee_inf`
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `CARD_ID` varchar(18) NOT NULL,
  `PHONE` varchar(11) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `SEX` int(11) NOT NULL DEFAULT 1,
  `PARTY` varchar(10) DEFAULT NULL,
  `RACE` varchar(100) DEFAULT NULL,
  `EDUCATION` varchar(10) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT current_timestamp(),
  `imgname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`),
  KEY `FK_EMP_JOB` (`JOB_ID`),
  CONSTRAINT `FK_EMP_DEPT` FOREIGN KEY (`DEPT_ID`) REFERENCES `dept_inf` (`ID`),
  CONSTRAINT `FK_EMP_JOB` FOREIGN KEY (`JOB_ID`) REFERENCES `job_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------
INSERT INTO `employee_inf` VALUES ('1', '123', '1', '8', '爱丽丝', '4328011988', '13902001111', '251425887@qq.com', '1', '党员', '满', '本科', '2016-03-14 11:35:18', null);
INSERT INTO `employee_inf` VALUES ('2', '1234', '2', '1', '杰克', '22623', '4247242', '251425887@qq.com', '2', '团员', '苗', '专科', '2016-03-14 11:35:18', null);
INSERT INTO `employee_inf` VALUES ('3', '123456', '1', '2', 'bb', '432801197711251038', '13907351532', '36750064@qq.com', '1', '党员', '汉', '本科', '2016-07-14 09:54:52', null);
INSERT INTO `employee_inf` VALUES ('4', '12345', '2', '2', 'mogul', '12345678', '18507078125', 'mogul@mogul.com', '2', '汗', '汗', '高中', '2021-04-30 00:00:00', '1619744523289_1619744523289_Capture001.png');

-- ----------------------------
-- Table structure for `job_inf`
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_inf
-- ----------------------------
INSERT INTO `job_inf` VALUES ('1', '职员', '职员;;');
INSERT INTO `job_inf` VALUES ('2', 'Java开发工程师', 'Java开发工程师');
INSERT INTO `job_inf` VALUES ('3', 'Java中级开发工程师', 'Java中级开发工程师');
INSERT INTO `job_inf` VALUES ('4', 'Java高级开发工程师', 'Java高级开发工程师');
INSERT INTO `job_inf` VALUES ('5', '系统管理员', '系统管理员');
INSERT INTO `job_inf` VALUES ('6', '架构师', '架构师');
INSERT INTO `job_inf` VALUES ('7', '主管', '主管');
INSERT INTO `job_inf` VALUES ('8', '经理', '经理');
INSERT INTO `job_inf` VALUES ('9', '总经理', '总经理');

-- ----------------------------
-- Table structure for `user_inf`
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) NOT NULL,
  `PASSWORD` varchar(16) NOT NULL,
  `STATUS` int(11) NOT NULL DEFAULT 1,
  `createdate` timestamp NOT NULL DEFAULT current_timestamp(),
  `username` varchar(20) DEFAULT NULL,
  `imgname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES ('1', 'admin', '123456', '1', '2016-03-12 09:34:28', '超级管理员', 'ui-sherman.jpg');
INSERT INTO `user_inf` VALUES ('2', 'gec', '123456', '0', '2018-04-09 07:04:03', '粤嵌', 'ui-sam.jpg');
INSERT INTO `user_inf` VALUES ('3', 'dfdssd', '123', '1', '2018-12-09 07:09:20', 'sdfdsf', 'ui-zac.jpg');
INSERT INTO `user_inf` VALUES ('4', 'tom', '123', '1', '2020-11-06 11:09:55', 'Tomcat', 'ui-danro.jpg');
INSERT INTO `user_inf` VALUES ('6', 'mjjjj', '123', '1', '2020-11-11 09:35:23', 'mjjjrrr', 'ui-zac.jpg');
