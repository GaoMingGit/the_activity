/*
 Navicat Premium Data Transfer

 Source Server         : 39.105.19.146
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : 39.105.19.146:3306
 Source Schema         : activity

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 10/10/2019 10:10:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activitytable
-- ----------------------------
DROP TABLE IF EXISTS `activitytable`;
CREATE TABLE `activitytable`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动表id',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  `activitypeople` int(11) DEFAULT NULL COMMENT '参与活动最大人数',
  `activitytitle` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '活动主题',
  `activitycontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '活动的内容',
  `activityendtime` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '活动终止时间',
  `activitystatus` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '活动状态(0代表进行，1代表结束)',
  `activitytype` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '1.运动 2.娱乐 3.电影 4.约饭 5.游戏 6.拼车 7.学习',
  `activitycreatetime` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '活动创建的时间',
  `activityaddress` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '活动的地点',
  `joinpeople` int(11) DEFAULT NULL COMMENT '活动参加的人数',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of activitytable
-- ----------------------------
INSERT INTO `activitytable` VALUES (1, 56, 20, '??', '???????', '11-11 08:40', '0', '1', '09-11 08:40', '????', 14);
INSERT INTO `activitytable` VALUES (3, 58, 1, '来个小姐姐陪看电影', '看完电影还可以去逛街吃饭', '09-11 17:31', '0', '3', '09-11 09:31', '碧桂园员工宿舍门口', 1);
INSERT INTO `activitytable` VALUES (4, 57, 5, 'utf8?', '??', '11-11 10:11', '0', '5', '09-11 10:11', '??', 11);
INSERT INTO `activitytable` VALUES (16, NULL, 5, 'no user', '??', '09-21 19:39', '0', '6', '09-13 19:39', '??', 1);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(5) NOT NULL COMMENT 'id',
  `adminname` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '管理员账号',
  `adminpassword` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'adminnimda');

-- ----------------------------
-- Table structure for ban_activity
-- ----------------------------
DROP TABLE IF EXISTS `ban_activity`;
CREATE TABLE `ban_activity`  (
  `bid` int(11) NOT NULL AUTO_INCREMENT COMMENT '举报信息表id',
  `aid` int(11) DEFAULT NULL COMMENT '被举报的活动id',
  `uid` int(11) DEFAULT NULL COMMENT '举报活动的用户id',
  `detail` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '被举报的原因',
  `bantime` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户举报的时间',
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ban_activity
-- ----------------------------
INSERT INTO `ban_activity` VALUES (1, 4, 56, '暴力', '2019-09-11 10:11');
INSERT INTO `ban_activity` VALUES (9, 3, 57, '色情', '19-9-11 下午12:37');
INSERT INTO `ban_activity` VALUES (10, 3, 57, '内容虚假', '19-9-11 下午12:40');
INSERT INTO `ban_activity` VALUES (11, 3, 57, '内容虚假', '19-9-11 下午12:41');
INSERT INTO `ban_activity` VALUES (12, 1, 57, '政治敏感', '19-9-11 下午12:47');
INSERT INTO `ban_activity` VALUES (13, 4, NULL, '内容虚假', '19-9-11 下午2:30');
INSERT INTO `ban_activity` VALUES (14, 4, NULL, '色情', '19-9-11 下午2:31');
INSERT INTO `ban_activity` VALUES (15, 4, NULL, '色情', '19-9-11 下午2:32');
INSERT INTO `ban_activity` VALUES (16, 1, 61, '内容虚假', '19-9-11 下午3:21');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户账号',
  `userpassword` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户密码',
  `usersex` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户性别（男和女）',
  `userphone` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户电话',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (56, 'mmm', '66d7846cfed3a180a3dcb24d922804b9', '男', '13113113121');
INSERT INTO `user` VALUES (57, 'heaon', 'ad202a10437a5ef9ecd7e1bdba614f79', '女', '13590799319');
INSERT INTO `user` VALUES (58, 'csl', '5785eba47edc8ba660affe3bbf7a348b', '男', '13645815345');
INSERT INTO `user` VALUES (59, '高铭', '4e095ff6688ae35f5c590cd498ddeec2', '男', '13169162754');
INSERT INTO `user` VALUES (61, 'qwe', '514f70e88fd768b45c04f1c51871b784', '男', '13169162754');
INSERT INTO `user` VALUES (62, 'lxm', '61c560dc32c129647e10cb9c05d680f5', '男', '13800138000');
INSERT INTO `user` VALUES (63, 'ljong', '571bd2c5df39f30bbf4ca91fa130d8d7', '男', '13590793319');

-- ----------------------------
-- Table structure for user_activity
-- ----------------------------
DROP TABLE IF EXISTS `user_activity`;
CREATE TABLE `user_activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `aid` int(11) DEFAULT NULL COMMENT '活动表id',
  `uid` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_activity
-- ----------------------------
INSERT INTO `user_activity` VALUES (1, 1, 56);
INSERT INTO `user_activity` VALUES (3, 3, 58);
INSERT INTO `user_activity` VALUES (4, 1, 57);
INSERT INTO `user_activity` VALUES (5, 4, 57);
INSERT INTO `user_activity` VALUES (8, 4, 58);
INSERT INTO `user_activity` VALUES (31, 1, 58);
INSERT INTO `user_activity` VALUES (32, 1, 58);
INSERT INTO `user_activity` VALUES (33, 1, 58);
INSERT INTO `user_activity` VALUES (34, 1, 58);
INSERT INTO `user_activity` VALUES (35, 1, 58);
INSERT INTO `user_activity` VALUES (36, 1, 58);
INSERT INTO `user_activity` VALUES (40, 4, 62);
INSERT INTO `user_activity` VALUES (41, 4, 62);
INSERT INTO `user_activity` VALUES (42, 4, 62);
INSERT INTO `user_activity` VALUES (43, 4, 62);
INSERT INTO `user_activity` VALUES (44, 4, 62);
INSERT INTO `user_activity` VALUES (45, 4, 62);
INSERT INTO `user_activity` VALUES (46, 4, 62);
INSERT INTO `user_activity` VALUES (47, 4, 62);
INSERT INTO `user_activity` VALUES (48, 4, 62);
INSERT INTO `user_activity` VALUES (49, 1, 62);
INSERT INTO `user_activity` VALUES (50, 1, 62);
INSERT INTO `user_activity` VALUES (51, 1, 62);
INSERT INTO `user_activity` VALUES (52, 1, 62);
INSERT INTO `user_activity` VALUES (53, 1, 62);
INSERT INTO `user_activity` VALUES (54, 1, 62);
INSERT INTO `user_activity` VALUES (55, 16, NULL);

SET FOREIGN_KEY_CHECKS = 1;
