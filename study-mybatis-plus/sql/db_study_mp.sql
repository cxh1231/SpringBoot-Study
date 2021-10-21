/*
 Navicat Premium Data Transfer

 Source Server         : chen
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : db_study_mp

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 21/10/2021 21:12:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `nickname` varchar(63) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `sex` tinyint(2) NULL DEFAULT NULL COMMENT '用户性别（0-未知 | 1-男 | 2-女）',
  `email` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(31) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `password` varchar(127) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '用户状态',
  `login_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `is_delete` tinyint(2) NULL DEFAULT 0 COMMENT '是否删除（0-未删除 | 1-已删除）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `delete_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10011 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (10000, '张三', NULL, NULL, '10000@domain.com', NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10011, '张三', NULL, NULL, '10000@domain.com', NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10012, '李四', NULL, NULL, '10012@domain.com', NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10013, '张三', NULL, NULL, '10000@domain.com', NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10014, '王五', NULL, NULL, '10013@domain.com', NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10015, '张三', NULL, NULL, '10000@domain.com', NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10016, '赵六', NULL, NULL, '10014@domain.com', NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10017, '测试用户名', NULL, NULL, '10066@domain.com', NULL, 'sfdnfjdsfhdsjk', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10018, '李四', NULL, NULL, '10066@domain.com', NULL, 'sfdnfjdsfhdsjk', NULL, NULL, NULL, 1, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10019, '用户101', NULL, NULL, '10101@domain.com', NULL, '2804df10cfd85d566a0fbb8d71f760b0', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10020, '用户102', NULL, NULL, '10102@domain.com', NULL, '6b37d1ec969838d29cb611deaff50a6b', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10021, '用户103', NULL, NULL, '10103@domain.com', NULL, 'c99f6758db3841cc311bb858bc46c273', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10022, '用户104', NULL, NULL, '10104@domain.com', NULL, '86e2e09ac77208621d1e5b0b870e9181', NULL, NULL, NULL, 0, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (10023, '用户105', NULL, NULL, '10105@domain.com', NULL, '8b811004869c0d33256eec2df927bb48', NULL, NULL, NULL, 0, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
