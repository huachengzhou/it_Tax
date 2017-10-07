/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : it_tax

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2017-10-07 16:54:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for complain
-- ----------------------------
DROP TABLE IF EXISTS `complain`;
CREATE TABLE `complain` (
  `comp_id` varchar(32) NOT NULL,
  `comp_company` varchar(100) DEFAULT NULL,
  `comp_name` varchar(20) DEFAULT NULL,
  `comp_mobile` varchar(20) DEFAULT NULL,
  `is_NM` tinyint(1) DEFAULT NULL,
  `comp_time` datetime DEFAULT NULL,
  `comp_title` varchar(200) NOT NULL,
  `to_comp_name` varchar(20) DEFAULT NULL,
  `to_comp_dept` varchar(100) DEFAULT NULL,
  `comp_content` text,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`comp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complain
-- ----------------------------
INSERT INTO `complain` VALUES ('1', 'n', 'bdgf', '1453464564', '1', '2017-09-27 17:51:57', '标题1', '张三', 'A部门', '的故事', '1');
INSERT INTO `complain` VALUES ('2', 'm', '是个', '46546464564', '2', '2017-09-20 19:00:53', '标题2', '李四', 'B部门', '三国杀', '0');
INSERT INTO `complain` VALUES ('3', 'h', '三国杀', '654578586656', '2', '2017-09-12 19:01:27', '标题3', '王维', 'A部门', '是否会对', '2');
INSERT INTO `complain` VALUES ('4', 'g', '速递公司', '5645855858585', '1', '2017-10-08 19:02:42', '标题4', '李白', 'B部门', '是否公司', '1');

-- ----------------------------
-- Table structure for complain_reply
-- ----------------------------
DROP TABLE IF EXISTS `complain_reply`;
CREATE TABLE `complain_reply` (
  `reply_id` varchar(32) NOT NULL,
  `comp_id` varchar(32) NOT NULL,
  `replyer` varchar(20) DEFAULT NULL,
  `reply_dept` varchar(100) DEFAULT NULL,
  `reply_time` datetime DEFAULT NULL,
  `reply_content` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `FK_comp_reply` (`comp_id`),
  CONSTRAINT `FK_comp_reply` FOREIGN KEY (`comp_id`) REFERENCES `complain` (`comp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of complain_reply
-- ----------------------------

-- ----------------------------
-- Table structure for emp_role
-- ----------------------------
DROP TABLE IF EXISTS `emp_role`;
CREATE TABLE `emp_role` (
  `emp_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`,`role_id`),
  KEY `FK_emp_role2` (`role_id`),
  CONSTRAINT `FK_emp_role` FOREIGN KEY (`emp_id`) REFERENCES `t_employee` (`emp_id`),
  CONSTRAINT `FK_emp_role2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emp_role
-- ----------------------------

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `info_id` varchar(32) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `content` longtext,
  `memo` varchar(200) DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('4028deed5e756f36015e757022de0000', 'tzgg', '百度', '信息标题1', '<p>??????ueditor!</p>', '时光大道虽然', '管理者', '2017-09-12 17:33:31', '1');
INSERT INTO `info` VALUES ('4028deed5e756f36015e757090b20001', 'tzgg', '百度', '信息标题2', '<p>??????ueditor!</p>', '萨嘎的人家何时', '管理者', '2017-09-12 17:33:58', '1');
INSERT INTO `info` VALUES ('4028deed5e75c4bc015e75c8a03c0000', 'tzgg', '百度', '端午放假', '<p>??????ueditor!</p>', '是让她加盟费机会', '管理者', '2017-09-12 19:10:25', '1');
INSERT INTO `info` VALUES ('4028deed5e75c4bc015e75c8fe6d0001', 'tzgg', '百度', '隔行扫描活佛济公', '<p>??????ueditor!</p>', 'rwtej', '管理者', '2017-09-12 19:10:39', '1');
INSERT INTO `info` VALUES ('4028deed5e75c4bc015e75c983960002', 'tzgg', '百度', '信息标题4', '<p>??阿娥鬼味人间特意日塑他可就腌过生日糊涂的价格可和????ueditor!</p>', '司法官倒海翻江', '管理者', '2017-09-12 19:11:15', '1');
INSERT INTO `info` VALUES ('4028deed5e75cfab015e75d0a95e0000', 'tzgg', '百度', '信息标题5', '', '无', '管理者', '2017-09-12 19:18:52', '1');
INSERT INTO `info` VALUES ('4028deed5e75cfab015e75d1751f0001', 'tzgg', '百度', '信息标题6', '<p>??????ueditor!</p>', 'xxxxxxxxxxxx', '管理者', '2017-09-12 19:19:54', '1');
INSERT INTO `info` VALUES ('4028deed5e75d96b015e75dae13c0000', 'tzgg', '百度', '信息标题3', '<p>??????ueditor!</p>', '阿娥个人化', '管理者', '2017-09-12 19:30:08', '1');
INSERT INTO `info` VALUES ('4028deed5e75d96b015e75dbdf9e0002', 'tzgg', 'google', '信息标题7', '<p>??????ueditor!</p>', '身上', '管理者', '2017-09-12 19:31:16', '1');

-- ----------------------------
-- Table structure for persons
-- ----------------------------
DROP TABLE IF EXISTS `persons`;
CREATE TABLE `persons` (
  `PID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `SEX` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persons
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('ff8080815e645001015e645157000000', '管理员', '1');
INSERT INTO `role` VALUES ('ff8080815e645001015e645196dc0001', '一般用户', '1');
INSERT INTO `role` VALUES ('ff8080815e645001015e6451ec830002', '一般用户A', '1');
INSERT INTO `role` VALUES ('ff8080815e699625015e6998062f0000', '游客', '1');

-- ----------------------------
-- Table structure for role_pri
-- ----------------------------
DROP TABLE IF EXISTS `role_pri`;
CREATE TABLE `role_pri` (
  `role_id` varchar(32) NOT NULL,
  `pri_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`pri_id`),
  KEY `FK_own` (`pri_id`),
  CONSTRAINT `FK_belong` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`),
  CONSTRAINT `FK_own` FOREIGN KEY (`pri_id`) REFERENCES `t_privilege` (`pri_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_pri
-- ----------------------------

-- ----------------------------
-- Table structure for role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `role_privilege`;
CREATE TABLE `role_privilege` (
  `role_id` varchar(32) NOT NULL,
  `code` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`,`code`),
  KEY `FK_9xg496yxp3mpqmnsdskahammb` (`role_id`),
  CONSTRAINT `FK_9xg496yxp3mpqmnsdskahammb` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_privilege
-- ----------------------------
INSERT INTO `role_privilege` VALUES ('ff8080815e645001015e645157000000', 'hqfw');
INSERT INTO `role_privilege` VALUES ('ff8080815e645001015e645157000000', 'nsfw');
INSERT INTO `role_privilege` VALUES ('ff8080815e645001015e645157000000', 'spaces');
INSERT INTO `role_privilege` VALUES ('ff8080815e645001015e645157000000', 'xzgl');
INSERT INTO `role_privilege` VALUES ('ff8080815e645001015e645157000000', 'zxxx');
INSERT INTO `role_privilege` VALUES ('ff8080815e645001015e645196dc0001', 'nsfw');
INSERT INTO `role_privilege` VALUES ('ff8080815e645001015e645196dc0001', 'spaces');
INSERT INTO `role_privilege` VALUES ('ff8080815e645001015e6451ec830002', 'hqfw');
INSERT INTO `role_privilege` VALUES ('ff8080815e645001015e6451ec830002', 'nsfw');
INSERT INTO `role_privilege` VALUES ('ff8080815e645001015e6451ec830002', 'xzgl');
INSERT INTO `role_privilege` VALUES ('ff8080815e699625015e6998062f0000', 'spaces');

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `detp_id` varchar(32) NOT NULL,
  `org_id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`detp_id`),
  KEY `FK_org_dept` (`org_id`),
  CONSTRAINT `FK_org_dept` FOREIGN KEY (`org_id`) REFERENCES `t_org` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept
-- ----------------------------

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `emp_id` varchar(32) NOT NULL,
  `detp_id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `FK_dept_emp` (`detp_id`),
  CONSTRAINT `FK_dept_emp` FOREIGN KEY (`detp_id`) REFERENCES `t_dept` (`detp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_employee
-- ----------------------------

-- ----------------------------
-- Table structure for t_leader
-- ----------------------------
DROP TABLE IF EXISTS `t_leader`;
CREATE TABLE `t_leader` (
  `emp_id` varchar(32) NOT NULL,
  `detp_id` varchar(32) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  CONSTRAINT `FK_Inheritance_1` FOREIGN KEY (`emp_id`) REFERENCES `t_employee` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leader
-- ----------------------------

-- ----------------------------
-- Table structure for t_org
-- ----------------------------
DROP TABLE IF EXISTS `t_org`;
CREATE TABLE `t_org` (
  `org_id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_org
-- ----------------------------

-- ----------------------------
-- Table structure for t_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_privilege`;
CREATE TABLE `t_privilege` (
  `pri_id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pri_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_privilege
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` varchar(255) NOT NULL,
  `DEPT` varchar(255) DEFAULT NULL,
  `ACCOUNT` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `HEADIMG` varchar(255) DEFAULT NULL,
  `GENDER` tinyint(1) DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `MOBILE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `MEMO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('ff8080815e660349015e660786fe0000', 'C部门', 'admin', '管理者', '123456', 'D:\\Java_ssh\\tomcat_and_jboss_as\\apache-tomcat-6.0.35\\webapps\\it_Tax\\upload\\f53be112-5929-48d8-898a-c9f77fea8271dog.jpg', '1', '1', '6547869708', 'admin@163.com', '2017-09-27 18:45:14', '无');
INSERT INTO `user` VALUES ('ff8080815e697b34015e697d25a50000', 'B部门', 'zhangsan', '张三', '123456', 'D:\\Java_ssh\\tomcat_and_jboss_as\\apache-tomcat-6.0.35\\webapps\\it_Tax\\upload\\dba68ee5-4a85-4e9a-b417-453a0d965887dog.jpg', '1', '1', '43567ui', 'zhangsan@163.com', '2017-09-28 09:52:28', '没有什么要说的');
INSERT INTO `user` VALUES ('ff8080815e699625015e699896670001', 'C部门', 'alice', '爱丽森', '123456', 'D:\\Java_ssh\\tomcat_and_jboss_as\\apache-tomcat-6.0.35\\webapps\\it_Tax\\upload\\01d1d6da-5c3a-4db7-a66e-6be8201e03fbtimgA.jpg', '0', '1', '6547869708', 'alice@fg.com', '2017-09-12 10:22:32', '优酷');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK_it77eq964jhfqtu54081ebtio` (`role_id`),
  CONSTRAINT `FK_it77eq964jhfqtu54081ebtio` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('ff8080815e645001015e645157000000', 'ff8080815e645001015e6453c2de0004');
INSERT INTO `user_role` VALUES ('ff8080815e645001015e645157000000', 'ff8080815e660349015e660786fe0000');
INSERT INTO `user_role` VALUES ('ff8080815e645001015e645196dc0001', 'ff8080815e645001015e6452bdca0003');
INSERT INTO `user_role` VALUES ('ff8080815e645001015e645196dc0001', 'ff8080815e660349015e660786fe0000');
INSERT INTO `user_role` VALUES ('ff8080815e645001015e645196dc0001', 'ff8080815e6640fc015e66424e4c0000');
INSERT INTO `user_role` VALUES ('ff8080815e645001015e645196dc0001', 'ff8080815e697b34015e697d25a50000');
INSERT INTO `user_role` VALUES ('ff8080815e645001015e6451ec830002', 'ff8080815e660349015e660786fe0000');
INSERT INTO `user_role` VALUES ('ff8080815e699625015e6998062f0000', 'ff8080815e699625015e699896670001');
