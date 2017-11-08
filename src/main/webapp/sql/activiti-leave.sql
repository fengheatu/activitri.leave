/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : activiti-leave

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-11-08 10:54:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for accraditation_record
-- ----------------------------
DROP TABLE IF EXISTS `accraditation_record`;
CREATE TABLE `accraditation_record` (
  `id` bigint(20) NOT NULL,
  `leave_id` bigint(20) NOT NULL COMMENT '假单id',
  `name` varchar(50) NOT NULL COMMENT '审批人',
  `accraditation_opinion` varchar(255) DEFAULT NULL COMMENT '审批意见',
  `is_agree` tinyint(4) NOT NULL COMMENT '是否同意 0-不同意 1-同意',
  `create_by` datetime NOT NULL COMMENT '创建者',
  `update_by` datetime NOT NULL COMMENT '更新者',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL COMMENT '逻辑删除标志，0-未删除，1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='假单审批记录';

-- ----------------------------
-- Records of accraditation_record
-- ----------------------------

-- ----------------------------
-- Table structure for leave
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '假单id',
  `user_id` bigint(20) NOT NULL COMMENT '请假人id',
  `leave_reason` varchar(255) NOT NULL COMMENT '请假理由',
  `leave_days` int(3) NOT NULL COMMENT '请假天数',
  `is_pass` tinyint(1) NOT NULL DEFAULT '0' COMMENT '假单是否审批通过 0-未通过 1-通过',
  `start_time` datetime NOT NULL COMMENT '请假开始时间',
  `end_time` datetime NOT NULL COMMENT '请假结束时间',
  `create_by` bigint(20) NOT NULL COMMENT '创建者',
  `update_by` bigint(20) NOT NULL COMMENT '更新者',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志，0-未删除，1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='请假单';

-- ----------------------------
-- Records of leave
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_by` bigint(20) NOT NULL,
  `update_by` bigint(20) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of user
-- ----------------------------
