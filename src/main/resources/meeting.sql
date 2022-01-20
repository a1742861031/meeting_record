/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : meeting

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 20/01/2022 20:01:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `person_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `record_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=331 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of attendance
-- ----------------------------
BEGIN;
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (312, '程老师', 44);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (313, '汪展', 44);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (314, '吴昊', 44);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (315, '刘浩', 44);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (316, '肖驿龙', 44);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (317, '祖建力', 44);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (318, '张彧烜', 44);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (319, '张博涵', 44);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (320, '唐于滟', 44);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (321, '程老师', 45);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (322, '阎波', 45);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (323, '汪展', 45);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (324, '吴昊', 45);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (325, '刘浩', 45);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (326, '肖驿龙', 45);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (327, '祖建力', 45);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (328, '张博涵', 45);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (329, '张彧烜', 45);
INSERT INTO `attendance` (`id`, `person_name`, `record_id`) VALUES (330, '唐于滟', 45);
COMMIT;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int NOT NULL AUTO_INCREMENT,
  `record_id` int DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` (`id`, `record_id`, `filename`, `url`) VALUES (58, 44, 'meeting.sql', 'https://guli-edu-bobo0125.oss-cn-chengdu.aliyuncs.com/meeting/9a55d7a993a64e3ebea9e581b058b3d7meeting.sql');
INSERT INTO `file` (`id`, `record_id`, `filename`, `url`) VALUES (59, 45, 'patent.sql', 'https://guli-edu-bobo0125.oss-cn-chengdu.aliyuncs.com/meeting/0da13f04f3c3449283a8a98d4e2d931apatent.sql');
COMMIT;

-- ----------------------------
-- Table structure for non_attendance
-- ----------------------------
DROP TABLE IF EXISTS `non_attendance`;
CREATE TABLE `non_attendance` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `person_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `record_id` bigint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of non_attendance
-- ----------------------------
BEGIN;
INSERT INTO `non_attendance` (`id`, `person_name`, `reason`, `record_id`) VALUES (74, '阎波', 'test', 44);
COMMIT;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
  `date` datetime DEFAULT NULL,
  `place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `read_num` bigint DEFAULT '0',
  `recorder` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of record
-- ----------------------------
BEGIN;
INSERT INTO `record` (`id`, `content`, `date`, `place`, `read_num`, `recorder`, `time`, `title`) VALUES (44, '<p>测试修改</p>', '2022-01-21 10:00:00', '江南分院16楼会议室', 11, '程老师', '17:46', '专利分析');
INSERT INTO `record` (`id`, `content`, `date`, `place`, `read_num`, `recorder`, `time`, `title`) VALUES (45, '<p>1、专利分析需求分为两个方面：一是以文献、特征词或图片为关键目标，以技术为导向进行精准检索，寻找与目标相匹配、接近甚至相反的内容（语义分析，附图相似度可作为辅助）；二是对某个行业进行宏观统计分析，找出一系列技术相关文献，制作专利地图，从而获悉特定技术的龙头企业等；</p><p>2、使用IPC进行检索范围控制。在审查过程中，可认定不同分类号但技术原理非常接近的专利。全库相关度排序，与references比较。关于分类号问题，例，中英互译，结果相互对比去重。语种是一个比较大的问题；</p><p>3、在专利生命周期中，从申请到授权或驳回期间专利审查员会引用对比文献，优先考虑驳回引用的文献。若宣告无效（区分于届满）时其引用的证据相关度、精确度会更高，后续还可考虑现有技术抗辩；</p><p>4、价值分析：届满日、转让记录、同族专利、相关度（如何区分专利过于密集反而没有价值/专利核心价值高）、专利布局空白、权利人（个人专利价值大于法人单位、科研院所比中小企业的有价值）、诉讼纠纷；</p><p>5、个性化推荐；</p><p>6、通过企业的专利布局可进行趋势预估；</p><p>7、下列是值得学习的产品：智慧芽、合享创新、patentics、orbit、德温特、美商联讯、联盈科技、wyps；</p><p>8、检索分析以英语为主，欧洲、日本。</p>', '2022-01-11 20:00:00', 'test', 19, '唐于滟', '17:48', '测试添加');
COMMIT;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of type
-- ----------------------------
BEGIN;
INSERT INTO `type` (`type_id`, `type_name`) VALUES (1, '老师');
INSERT INTO `type` (`type_id`, `type_name`) VALUES (2, '研一');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '成员id',
  `user_type` int NOT NULL COMMENT '学生的类型 研一 研二 研三 以及老师 超级管理员',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码 管理系统添加应该有一个初始密码',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `user_mobile` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电话',
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学生邮件',
  `is_admin` tinyint(1) DEFAULT NULL COMMENT '是否是管理员',
  `is_locked` tinyint(1) DEFAULT '1' COMMENT '账户是否被锁定',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`user_id`, `user_type`, `user_password`, `user_avatar`, `user_name`, `user_mobile`, `user_email`, `is_admin`, `is_locked`) VALUES (2, 2, '$2a$10$smxKGfX.MbjXcsGvpa27QuYqaE.filtkNBZeWq6tZvD2ndT0fjKjO', 'https://guli-edu-bobo0125.oss-cn-chengdu.aliyuncs.com/meeting/4c421e4dc68e4462a418a63aa51f67b5u=3017424451,1007193948&fm=253&fmt=auto&app=138&f=JPEG.webp', '阎波', '18323667740', '2460417845@qq.com', 1, 0);
INSERT INTO `user` (`user_id`, `user_type`, `user_password`, `user_avatar`, `user_name`, `user_mobile`, `user_email`, `is_admin`, `is_locked`) VALUES (12, 2, '$2a$10$qLZx/DuxTQEfTUciXksagOq1.2jJVHM7ZWionY2lKYpnQ4NtA.Z7e', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F67%2F59%2F63%2F58e8ebdd5c471.png&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644763116&t=dbe615b875138c9baca1477fd58b5590', '汪展', '', NULL, 1, 0);
INSERT INTO `user` (`user_id`, `user_type`, `user_password`, `user_avatar`, `user_name`, `user_mobile`, `user_email`, `is_admin`, `is_locked`) VALUES (13, 2, '$2a$10$Bbp8mmhRbs0r.qx.VYCvqea.WA.NR5nTsHkjI4ZAQh.GJhjf.3Qvu', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F67%2F59%2F63%2F58e8ebdd5c471.png&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644763116&t=dbe615b875138c9baca1477fd58b5590', '吴昊', '', NULL, 1, 0);
INSERT INTO `user` (`user_id`, `user_type`, `user_password`, `user_avatar`, `user_name`, `user_mobile`, `user_email`, `is_admin`, `is_locked`) VALUES (14, 2, '$2a$10$RBAYYIjeZ9EzCyVwIfhe2ey84mcYAaS8Z11DP0wR6iPPdsVB//dS2', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F67%2F59%2F63%2F58e8ebdd5c471.png&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644763116&t=dbe615b875138c9baca1477fd58b5590', '刘浩', '', NULL, 1, 0);
INSERT INTO `user` (`user_id`, `user_type`, `user_password`, `user_avatar`, `user_name`, `user_mobile`, `user_email`, `is_admin`, `is_locked`) VALUES (15, 1, '$2a$10$2z7Bsr7Qb6nejZ2Qpg.J1OxASgdvbVFpYYQl44zGYFk3asWhVYdy2', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F67%2F59%2F63%2F58e8ebdd5c471.png&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644763116&t=dbe615b875138c9baca1477fd58b5590', '程老师', '', NULL, 1, 0);
INSERT INTO `user` (`user_id`, `user_type`, `user_password`, `user_avatar`, `user_name`, `user_mobile`, `user_email`, `is_admin`, `is_locked`) VALUES (18, 2, '$2a$10$LitZWkCxvnx0N7nDgyO.IuOjs/kZrq4LXAh3uulOn07/xjUeCs0DK', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F67%2F59%2F63%2F58e8ebdd5c471.png&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644763116&t=dbe615b875138c9baca1477fd58b5590', '肖驿龙', '', NULL, 1, 0);
INSERT INTO `user` (`user_id`, `user_type`, `user_password`, `user_avatar`, `user_name`, `user_mobile`, `user_email`, `is_admin`, `is_locked`) VALUES (19, 2, '$2a$10$cJTvjRE6PvL2ZTzd06ndwuEzG0RVIGs.e0.3zR2RMu9bGXciCnlIS', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F67%2F59%2F63%2F58e8ebdd5c471.png&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644763116&t=dbe615b875138c9baca1477fd58b5590', '祖建力', '', NULL, 1, 0);
INSERT INTO `user` (`user_id`, `user_type`, `user_password`, `user_avatar`, `user_name`, `user_mobile`, `user_email`, `is_admin`, `is_locked`) VALUES (20, 2, '$2a$10$4Fybv6Buoh.AVvaXAZvPj.lCfQ9bPL9C0WE2CemrKEXLOmu7.7xF.', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F67%2F59%2F63%2F58e8ebdd5c471.png&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644763116&t=dbe615b875138c9baca1477fd58b5590', '张彧烜', '', NULL, 1, 0);
INSERT INTO `user` (`user_id`, `user_type`, `user_password`, `user_avatar`, `user_name`, `user_mobile`, `user_email`, `is_admin`, `is_locked`) VALUES (21, 2, '$2a$10$l52N3H1BUSXteop6VoajDO78JWY3BNioUctx/wkhIOb61/zhG6h2.', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F67%2F59%2F63%2F58e8ebdd5c471.png&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644763116&t=dbe615b875138c9baca1477fd58b5590', '张博涵', '', NULL, 1, 0);
INSERT INTO `user` (`user_id`, `user_type`, `user_password`, `user_avatar`, `user_name`, `user_mobile`, `user_email`, `is_admin`, `is_locked`) VALUES (22, 2, '$2a$10$WgDdfXr6WqjQNgEfYPyVRum5qTCItH9UTT8wR.ithWf9bCTDfIgkK', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F67%2F59%2F63%2F58e8ebdd5c471.png&refer=http%3A%2F%2Fpic.90sjimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644763116&t=dbe615b875138c9baca1477fd58b5590', '唐于滟', '', NULL, 1, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
