/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : loop_admin_v0

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-03-03 20:17:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `company_id` char(32) NOT NULL,
  `company_name` char(32) DEFAULT NULL,
  `parent_id` char(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `company_num` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_company
-- ----------------------------

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary` (
  `dictionary_id` char(32) NOT NULL,
  `dic_value` varchar(255) DEFAULT NULL,
  `parent_id` char(32) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `dic_num` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`dictionary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `log_id` char(32) NOT NULL,
  `log_value` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `option_persion` char(32) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `gets` varchar(10240) DEFAULT NULL,
  `posts` varchar(10240) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` varchar(255) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `menu_remark` varchar(255) DEFAULT NULL,
  `menu_parent_id` varchar(255) DEFAULT NULL,
  `menu_icon` varchar(255) DEFAULT NULL,
  `menu_eng_name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_perm_role
-- ----------------------------
DROP TABLE IF EXISTS `t_perm_role`;
CREATE TABLE `t_perm_role` (
  `role_id` varchar(64) NOT NULL,
  `role_name` varchar(64) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_perm_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_perm_user
-- ----------------------------
DROP TABLE IF EXISTS `t_perm_user`;
CREATE TABLE `t_perm_user` (
  `user_perm_id` char(32) NOT NULL,
  `user_account` varchar(50) DEFAULT NULL,
  `user_pass` varchar(50) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_gender` varchar(10) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `user_workid` varchar(50) DEFAULT NULL COMMENT '临时阅卷老师所阅的cuttingMergId',
  `company_id` char(32) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `is_temp` varchar(255) DEFAULT NULL COMMENT '是否是临时账号',
  `is_subject` varchar(255) DEFAULT NULL COMMENT '是否科组长',
  PRIMARY KEY (`user_perm_id`),
  KEY `company_id` (`company_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_perm_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `role_id` varchar(64) NOT NULL,
  `menu_id` varchar(64) NOT NULL,
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `function_id` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `role_id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
