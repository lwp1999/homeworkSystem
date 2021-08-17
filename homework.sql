/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : homework

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 14/08/2021 17:57:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for create_class
-- ----------------------------
DROP TABLE IF EXISTS `create_class`;
CREATE TABLE `create_class`  (
  `class_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_sum` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of create_class
-- ----------------------------
INSERT INTO `create_class` VALUES ('120', '123456', '1200', '123456', 1);
INSERT INTO `create_class` VALUES ('1234565', '123456', 'zjyb', '123456', 0);
INSERT INTO `create_class` VALUES ('123456的班级', '123456', '终极一班', '123456', 0);
INSERT INTO `create_class` VALUES ('180101', '12345', '22', '123456', 1);
INSERT INTO `create_class` VALUES ('1801012 ', '123456', '22', '123456', 0);

-- ----------------------------
-- Table structure for join_class
-- ----------------------------
DROP TABLE IF EXISTS `join_class`;
CREATE TABLE `join_class`  (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `class_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of join_class
-- ----------------------------
INSERT INTO `join_class` VALUES ('', '');
INSERT INTO `join_class` VALUES ('123456', '120');
INSERT INTO `join_class` VALUES ('123456', '180101');

-- ----------------------------
-- Table structure for publish_homework
-- ----------------------------
DROP TABLE IF EXISTS `publish_homework`;
CREATE TABLE `publish_homework`  (
  `ph_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ph_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ph_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `class_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ph_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `riqi` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ph_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of publish_homework
-- ----------------------------
INSERT INTO `publish_homework` VALUES (1, '123456', '第一次作业', '11', '120', '', '2021/1/16 下午7:50:51');
INSERT INTO `publish_homework` VALUES (2, '123456', '第二次作业', '圆周率是多少', '120', NULL, '2021/1/17 下午5:07:05');
INSERT INTO `publish_homework` VALUES (3, '123456', '第三次作业', '啊啊啊啊阿', '120', NULL, '2021/1/17 下午5:27:41');

-- ----------------------------
-- Table structure for submit_homework
-- ----------------------------
DROP TABLE IF EXISTS `submit_homework`;
CREATE TABLE `submit_homework`  (
  `sub_id` int(0) NOT NULL AUTO_INCREMENT,
  `ph_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sub_content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sub_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sub_dat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sub_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submit_homework
-- ----------------------------
INSERT INTO `submit_homework` VALUES (1, '1', '123456', 'l', '120', '我重新写好了', '', '2021/1/16 下午11:46:58');
INSERT INTO `submit_homework` VALUES (3, '3', '123456', 'l', '120', '222', '', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `school` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `qq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('12345', 'lwp', '123456', 'hzu', '6317', '17620', '6317', '男');
INSERT INTO `user` VALUES ('123456', 'l', '123456', 'hzu', '12345', '134111', NULL, '男');
INSERT INTO `user` VALUES ('123456789', '180101', '123456789', '', '', '', '', NULL);

SET FOREIGN_KEY_CHECKS = 1;
