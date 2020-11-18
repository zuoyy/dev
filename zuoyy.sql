/*
 Navicat MySQL Data Transfer

 Source Server         : bonday
 Source Server Type    : MySQL
 Source Server Version : 50616
 Source Host           : rm-bp1x460hk41883xq0o.mysql.rds.aliyuncs.com:3306
 Source Schema         : zuoyy

 Target Server Type    : MySQL
 Target Server Version : 50616
 File Encoding         : 65001

 Date: 18/11/2020 15:53:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for sys_action_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_action_log`;
CREATE TABLE `sys_action_log`  (
  `id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `clazz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `ipaddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `record_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `oper_by` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK32gm4dja0jetx58r9dc2uljiu`(`oper_by`) USING BTREE,
  CONSTRAINT `FK32gm4dja0jetx58r9dc2uljiu` FOREIGN KEY (`oper_by`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_by` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_by` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_jo1fjjm4s9wpm1432vi2cn3c6`(`create_by`) USING BTREE,
  INDEX `FK_5dbc3rg1xj6uy22hn89m7s8j2`(`update_by`) USING BTREE,
  CONSTRAINT `FK_5dbc3rg1xj6uy22hn89m7s8j2` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_jo1fjjm4s9wpm1432vi2cn3c6` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_by` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_by` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_pletj6lw7lkmi2v75qf0ptfa3`(`create_by`) USING BTREE,
  INDEX `FK_w37i6we6n67anwjwd89h5r30`(`update_by`) USING BTREE,
  CONSTRAINT `FK_pletj6lw7lkmi2v75qf0ptfa3` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_w37i6we6n67anwjwd89h5r30` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('3FuSNBxhQ52kSMeWAWwL9A', '2018-10-05 20:24:57', '', 1, '2018-10-13 13:56:05', '1', '1', 'MENU_TYPE', '菜单类型', 1, '1:菜单导航,2:页面按钮,3:表格按钮,4:其它资源');
INSERT INTO `sys_dict` VALUES ('De0zV-vnTw-IZ9kE-9vlYw', '2018-10-05 20:12:32', '', 1, '2018-10-05 20:12:32', '1', '1', 'USER_SEX', '用户性别', 1, '1:男,2:女');
INSERT INTO `sys_dict` VALUES ('ickBcmy5RiSesATtLAryjA', '2018-10-05 16:03:11', '', 1, '2018-10-05 16:11:41', '1', '1', 'DATA_STATUS', '数据状态', 1, '1:正常,2:冻结,3:删除');
INSERT INTO `sys_dict` VALUES ('ijjLYjkHSPWLxdJ7HPdboQ', '2018-10-05 20:08:55', '', 1, '2019-06-26 21:42:57', '1', '1', 'DICT_TYPE', '字典类型', 1, '1:系统类型,2:非系统类型');
INSERT INTO `sys_dict` VALUES ('k6703KhISf6LnGdJn8DkqQ', '2018-10-05 20:28:47', '', 1, '2019-02-26 00:31:43', '1', '1', 'LOG_TYPE', '日志类型', 1, '1:业务,2:登录,3:系统');
INSERT INTO `sys_dict` VALUES ('n9ecQiOXS5euxEuJqDHKvw', '2018-10-05 20:25:45', '', 1, '2019-02-26 00:34:34', '1', '1', 'SEARCH_STATUS', '搜索栏状态', 1, '1:正常,2:冻结');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sha1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `size` bigint(20) NULL DEFAULT NULL,
  `create_by` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKbdd4wds55d8pumw4osf6v687s`(`create_by`) USING BTREE,
  CONSTRAINT `FKbdd4wds55d8pumw4osf6v687s` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_by` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_by` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `btn_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `btn_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `btn_onclick` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `us_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_chmy2umajqqsxs72xupaw2bp2`(`create_by`) USING BTREE,
  INDEX `FK_qpmp4gcxex623foonm8iatf2s`(`update_by`) USING BTREE,
  CONSTRAINT `FK_chmy2umajqqsxs72xupaw2bp2` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_qpmp4gcxex623foonm8iatf2s` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('-OwLbuQOQOSaMqss0ad_4w', '2019-06-27 22:49:07', NULL, 1, '2019-06-27 22:50:06', '1', '1', 'btn btn-info btn-xs', '', 'detail', NULL, 3, 'system:dict:detail', 'kfl1px9tSh2Lknu53nXCrQ', '[0],[oiCCCvXeR02ingDDWkzyEA],[kfl1px9tSh2Lknu53nXCrQ]', 3, '详情', 3, '', 'Detail');
INSERT INTO `sys_menu` VALUES ('0crmrv4LRd2R2NzBvaxZAw', '2019-06-26 03:35:02', NULL, 1, '2019-06-26 03:39:25', '1', '1', 'btn btn-danger btn-xs', '', 'del', NULL, 3, 'system:actionLog:delete', 'hSFJ-nJ-Q8uYDh04FFTllQ', '[0],[oiCCCvXeR02ingDDWkzyEA],[hSFJ-nJ-Q8uYDh04FFTllQ]', 3, '删除', 3, '', 'Delete');
INSERT INTO `sys_menu` VALUES ('10D5A1XgSZeqtk6S2z59aA', '2019-06-25 22:27:39', NULL, 1, '2019-06-30 21:17:04', '1', '1', 'btn btn-danger btn-xs', '', 'del', NULL, 3, 'system:role:delete', 'qr-4mPULR9G3kUXNZuwUfg', '[0],[oiCCCvXeR02ingDDWkzyEA],[qr-4mPULR9G3kUXNZuwUfg]', 5, '删除', 3, '', 'Delete');
INSERT INTO `sys_menu` VALUES ('1M9-rGcRSFe8vBls0PI5Jg', '2019-06-25 21:46:56', NULL, 1, '2019-06-25 21:46:56', '1', '1', '', '', '', NULL, 2, 'system:user:index', 'oiCCCvXeR02ingDDWkzyEA', '[0],[oiCCCvXeR02ingDDWkzyEA]', 1, '用户管理', 1, '/system/user/index.shtml', 'User Management');
INSERT INTO `sys_menu` VALUES ('5iz7y-dVQmCdY7Ea5EnhdQ', '2019-06-27 22:51:17', NULL, 1, '2019-06-30 21:17:04', '1', '1', 'btn btn-info btn-xs', '', 'detail', NULL, 3, 'system:role:detail', 'qr-4mPULR9G3kUXNZuwUfg', '[0],[oiCCCvXeR02ingDDWkzyEA],[qr-4mPULR9G3kUXNZuwUfg]', 4, '详情', 3, '', 'Detail');
INSERT INTO `sys_menu` VALUES ('BeQB2ybKRGC3bdQffHAqdg', '2019-06-26 04:36:29', NULL, 1, '2019-06-26 04:36:29', '1', '1', 'btn btn-success btn-xs', '', 'edit', NULL, 3, 'system:dict:edit', 'kfl1px9tSh2Lknu53nXCrQ', '[0],[oiCCCvXeR02ingDDWkzyEA],[kfl1px9tSh2Lknu53nXCrQ]', 2, '编辑', 3, '', 'Edit');
INSERT INTO `sys_menu` VALUES ('bGvPGUNqR16AD03P4uSNmQ', '2019-06-26 03:31:03', NULL, 1, '2019-06-30 22:31:13', '1', '1', 'btn btn-warning btn-sm', 'btn-empty', '', NULL, 3, 'system:actionLog:empty', 'hSFJ-nJ-Q8uYDh04FFTllQ', '[0],[oiCCCvXeR02ingDDWkzyEA],[hSFJ-nJ-Q8uYDh04FFTllQ]', 1, '清空', 2, '', 'Empty');
INSERT INTO `sys_menu` VALUES ('HApVK8xDRdubyEZZPlTt5w', '2019-06-27 22:52:35', NULL, 1, '2019-06-27 22:52:35', '1', '1', 'btn btn-success btn-xs', '', 'edit', NULL, 3, 'system:user:edit', '1M9-rGcRSFe8vBls0PI5Jg', '[0],[oiCCCvXeR02ingDDWkzyEA],[1M9-rGcRSFe8vBls0PI5Jg]', 2, '编辑', 3, '', 'Edit');
INSERT INTO `sys_menu` VALUES ('hSFJ-nJ-Q8uYDh04FFTllQ', '2019-06-25 21:49:26', NULL, 1, '2019-06-25 21:49:26', '1', '1', '', '', '', NULL, 2, 'system:actionLog:index', 'oiCCCvXeR02ingDDWkzyEA', '[0],[oiCCCvXeR02ingDDWkzyEA]', 4, '日志管理', 1, '/system/actionLog/index.shtml', 'Log management');
INSERT INTO `sys_menu` VALUES ('J64cazUEQQiC8esr96Fr9Q', '2020-07-31 03:15:01', NULL, 3, '2020-08-20 19:36:28', '1', '1', '', '', '', 'fa fa-lg fa-fw fa-wrench', 1, '#', '0', '[0]', 2, '常用工具', 1, '#', 'Tools');
INSERT INTO `sys_menu` VALUES ('Kb7UrlgfTtafQsxAYoOU5A', '2019-06-27 22:35:08', NULL, 1, '2019-06-30 21:17:04', '1', '1', 'btn btn-success btn-xs', '', 'edit', NULL, 3, 'system:role:edit', 'qr-4mPULR9G3kUXNZuwUfg', '[0],[oiCCCvXeR02ingDDWkzyEA],[qr-4mPULR9G3kUXNZuwUfg]', 3, '编辑', 3, '', 'Edit');
INSERT INTO `sys_menu` VALUES ('kfl1px9tSh2Lknu53nXCrQ', '2019-06-25 21:48:29', NULL, 1, '2019-06-25 21:48:29', '1', '1', '', '', '', NULL, 2, 'system:dict:index', 'oiCCCvXeR02ingDDWkzyEA', '[0],[oiCCCvXeR02ingDDWkzyEA]', 3, '数据字典', 1, '/system/dict/index.shtml', 'Data Dictionary');
INSERT INTO `sys_menu` VALUES ('knd7ZQj7QRmLm8rubWC-2A', '2019-06-26 10:35:35', NULL, 1, '2019-07-01 05:23:01', '1', '1', '', '', '', NULL, 2, 'system:menu:index', 'oiCCCvXeR02ingDDWkzyEA', '[0],[oiCCCvXeR02ingDDWkzyEA]', 5, '权限管理', 1, '/system/menu/index.shtml', 'Authority management');
INSERT INTO `sys_menu` VALUES ('MG8BPrNwRRafeBHcug74qg', '2019-06-27 22:53:31', NULL, 1, '2019-06-27 22:53:31', '1', '1', 'btn btn-danger btn-xs', '', 'del', NULL, 3, 'system:user:delete', '1M9-rGcRSFe8vBls0PI5Jg', '[0],[oiCCCvXeR02ingDDWkzyEA],[1M9-rGcRSFe8vBls0PI5Jg]', 4, '删除', 3, '', 'Delete');
INSERT INTO `sys_menu` VALUES ('oiCCCvXeR02ingDDWkzyEA', '2019-06-26 10:34:23', NULL, 1, '2020-08-03 02:36:52', '1', '1', '', '', '', 'fa fa-lg fa-fw fa-cog', 1, '#', '0', '[0]', 3, '系统管理', 1, '#', 'System Management');
INSERT INTO `sys_menu` VALUES ('otZp-FGbR_eA1fRkcj9NxA', '2019-06-26 04:35:24', NULL, 1, '2019-06-30 22:31:05', '1', '1', 'btn btn-primary btn-sm', 'btn-add', '', NULL, 3, 'system:dict:add', 'kfl1px9tSh2Lknu53nXCrQ', '[0],[oiCCCvXeR02ingDDWkzyEA],[kfl1px9tSh2Lknu53nXCrQ]', 1, '新增', 2, '', 'Add');
INSERT INTO `sys_menu` VALUES ('qr-4mPULR9G3kUXNZuwUfg', '2019-06-25 21:47:31', NULL, 1, '2019-06-25 21:47:31', '1', '1', '', '', '', NULL, 2, 'system:role:index', 'oiCCCvXeR02ingDDWkzyEA', '[0],[oiCCCvXeR02ingDDWkzyEA]', 2, '角色管理', 1, '/system/role/index.shtml', 'Role management');
INSERT INTO `sys_menu` VALUES ('tj2aeMqyTB6Sjl5doj_E5Q', '2019-06-26 04:37:11', NULL, 1, '2019-06-27 22:50:06', '1', '1', 'btn btn-danger btn-xs', '', 'del', NULL, 3, 'system:dict:delete', 'kfl1px9tSh2Lknu53nXCrQ', '[0],[oiCCCvXeR02ingDDWkzyEA],[kfl1px9tSh2Lknu53nXCrQ]', 4, '删除', 3, '', 'Delete');
INSERT INTO `sys_menu` VALUES ('t_9E1YqpRNSiqqujNd4JpQ', '2020-07-31 03:16:39', NULL, 3, '2020-08-20 19:36:19', '1', '1', '', '', '', NULL, 2, 'tools:email:index', 'J64cazUEQQiC8esr96Fr9Q', '[0],[J64cazUEQQiC8esr96Fr9Q]', 1, '邮件发送', 1, '/tools/email/index.shtml', 'Email');
INSERT INTO `sys_menu` VALUES ('uL-MQjrVRjmVSDFwriLGaw', '2019-06-26 02:47:19', NULL, 1, '2019-06-30 22:30:45', '1', '1', 'btn btn-primary btn-sm', 'btn-add', '', NULL, 3, 'system:user:add', '1M9-rGcRSFe8vBls0PI5Jg', '[0],[oiCCCvXeR02ingDDWkzyEA],[1M9-rGcRSFe8vBls0PI5Jg]', 1, '新增', 2, '/system', 'Add');
INSERT INTO `sys_menu` VALUES ('vrtMVgX2QRO3GrBm6FqY-g', '2019-06-25 22:25:15', NULL, 1, '2019-06-30 22:30:56', '1', '1', 'btn btn-primary btn-sm', 'btn-add', '', NULL, 3, 'system:role:add', 'qr-4mPULR9G3kUXNZuwUfg', '[0],[oiCCCvXeR02ingDDWkzyEA],[qr-4mPULR9G3kUXNZuwUfg]', 1, '新增', 2, '', 'Add');
INSERT INTO `sys_menu` VALUES ('Y3Cmg_4HQ7yGOq1zydn50Q', '2019-06-25 21:50:22', NULL, 1, '2019-06-25 21:50:22', '1', '1', '', '', '', NULL, 2, 'system:druid:index', 'oiCCCvXeR02ingDDWkzyEA', '[0],[oiCCCvXeR02ingDDWkzyEA]', 6, '数据监控', 1, '/system/druid/index.shtml', 'Data monitoring');
INSERT INTO `sys_menu` VALUES ('ydHcpScmSZSIj-VhkHTZrg', '2019-06-26 03:34:07', NULL, 1, '2019-06-26 03:34:07', '1', '1', 'btn btn-info btn-xs', '', 'detail', NULL, 3, 'system:actionLog:detail', 'hSFJ-nJ-Q8uYDh04FFTllQ', '[0],[oiCCCvXeR02ingDDWkzyEA],[hSFJ-nJ-Q8uYDh04FFTllQ]', 2, '详情', 3, '', 'Detail');
INSERT INTO `sys_menu` VALUES ('zI6tgwSrQpOrmRDV3MTawg', '2019-06-26 02:48:28', NULL, 1, '2019-06-27 22:52:35', '1', '1', 'btn btn-info btn-xs', '', 'detail', NULL, 3, 'system:user:detail', '1M9-rGcRSFe8vBls0PI5Jg', '[0],[oiCCCvXeR02ingDDWkzyEA],[1M9-rGcRSFe8vBls0PI5Jg]', 3, '详情', 3, '', 'Detail');
INSERT INTO `sys_menu` VALUES ('Zi7bbOmjTk63ZEvhd7u9yw', '2019-06-30 21:17:04', NULL, 1, '2019-06-30 21:17:04', '1', '1', 'btn btn-warning btn-sm', 'btn-auth', '', NULL, 3, 'system:role:auth', 'qr-4mPULR9G3kUXNZuwUfg', '[0],[oiCCCvXeR02ingDDWkzyEA],[qr-4mPULR9G3kUXNZuwUfg]', 2, '授权', 2, '', 'Auth');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `create_by` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_by` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_f1tuelx21febfjdks0dgbc00r`(`create_by`) USING BTREE,
  INDEX `FK_9fhw9abenrp9xf4tapimn8h9u`(`update_by`) USING BTREE,
  CONSTRAINT `FK_9fhw9abenrp9xf4tapimn8h9u` FOREIGN KEY (`update_by`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_f1tuelx21febfjdks0dgbc00r` FOREIGN KEY (`create_by`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('6I6ytZfQR4O0_168MOyelw', '2019-06-26 10:33:07', NULL, 1, '2019-07-01 05:07:13', '1', '1', 'superAdmin', '超级管理员');
INSERT INTO `sys_role` VALUES ('XjtlFShtQ1eSUPtJGvXsTQ', '2019-07-01 05:56:02', '', 1, '2020-09-21 12:22:19', '1', '1', 'user', '普通用户');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `FKf3mud4qoc7ayew8nml4plkevo`(`menu_id`) USING BTREE,
  CONSTRAINT `FKf3mud4qoc7ayew8nml4plkevo` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKkeitxsgxwayackgqllio4ohn5` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('XjtlFShtQ1eSUPtJGvXsTQ', 'bepCzA0WTAGGb6vApk0wiQ');
INSERT INTO `sys_role_menu` VALUES ('XjtlFShtQ1eSUPtJGvXsTQ', 'H7vAOVORSF-PrZLCWfE47A');
INSERT INTO `sys_role_menu` VALUES ('XjtlFShtQ1eSUPtJGvXsTQ', 'hgXTLZyMSnqinCszUE2vcQ');
INSERT INTO `sys_role_menu` VALUES ('XjtlFShtQ1eSUPtJGvXsTQ', 'jmXh43UWQBWyaOUmgObacg');
INSERT INTO `sys_role_menu` VALUES ('XjtlFShtQ1eSUPtJGvXsTQ', 'KKcmqhGpT8SfLhLbBuNb1A');
INSERT INTO `sys_role_menu` VALUES ('XjtlFShtQ1eSUPtJGvXsTQ', 'oy13m3MbQ-Ou3utwdfYxQg');
INSERT INTO `sys_role_menu` VALUES ('XjtlFShtQ1eSUPtJGvXsTQ', 'QB94Kh6wTsKIpiKszCpnLg');
INSERT INTO `sys_role_menu` VALUES ('XjtlFShtQ1eSUPtJGvXsTQ', 'Tk9MugzeRCKhEVT3c9fs1A');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKb3pkx0wbo6o8i8lj0gxr37v1n`(`dept_id`) USING BTREE,
  CONSTRAINT `FKb3pkx0wbo6o8i8lj0gxr37v1n` FOREIGN KEY (`dept_id`) REFERENCES `sys_dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '2019-06-26 10:31:52', NULL, 'zh-CN', '左哥', '123a5155af9e2aff37506686cc627095f294087e5a645d70c5e2c182f328407b', '10086', NULL, NULL, 'J2Uq8q', '1', 1, '2020-07-31 03:48:14', 'admin', NULL);
INSERT INTO `sys_user` VALUES ('GiRR_QFbTC-ttGxxne4mhQ', '2020-08-04 06:17:13', '110@qq.com', 'zh-CN', 'Brian', '9caebfb98b593450831cd2bc5bea29ad29aedf283478717acb129cbcb7987cda', '11111111111', NULL, '', 'U1r5hV', '1', 1, '2020-08-04 06:17:13', 'brian', NULL);
INSERT INTO `sys_user` VALUES ('L0UlsGgjTA-5rD2-_gvnmA', '2019-07-01 05:56:48', 'zuoge@live.com', NULL, 'zuo', '95a860e33a6f0187af072c16a93ed03d590061900cb163f720893ed0e45c2f05', '18616731626', NULL, '', 'qXCaYp', '1', 1, '2020-08-27 19:56:07', 'zuo2', NULL);
INSERT INTO `sys_user` VALUES ('mzgPT7yfSMGofmBNA0jAxA', '2020-08-23 13:04:00', 'chuhan@cnextgen.com', 'zh-CN', 'ChuHan', '8116f5fbe25fb0b2be13a8a824f9f08b0afc9ee80c12a4498361f9cc68d76f3f', '11111111111', NULL, '', 'bFIU0S', '2', 1, '2020-08-23 13:04:00', 'chuhan', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FKhh52n8vd4ny9ff4x9fb8v65qx`(`role_id`) USING BTREE,
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '6I6ytZfQR4O0_168MOyelw');
INSERT INTO `sys_user_role` VALUES ('GiRR_QFbTC-ttGxxne4mhQ', 'XjtlFShtQ1eSUPtJGvXsTQ');
INSERT INTO `sys_user_role` VALUES ('L0UlsGgjTA-5rD2-_gvnmA', 'XjtlFShtQ1eSUPtJGvXsTQ');
INSERT INTO `sys_user_role` VALUES ('mzgPT7yfSMGofmBNA0jAxA', 'XjtlFShtQ1eSUPtJGvXsTQ');

SET FOREIGN_KEY_CHECKS = 1;
