/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : shipping

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 26/04/2020 20:29:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mqtt_client
-- ----------------------------
DROP TABLE IF EXISTS `mqtt_client`;
CREATE TABLE `mqtt_client`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `clientid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `node` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `online_at` datetime(0) NULL DEFAULT NULL,
  `offline_at` datetime(0) NULL DEFAULT NULL,
  `created` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `valid` int(0) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `mqtt_client_key`(`clientid`) USING BTREE,
  INDEX `mqtt_client_idx`(`clientid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg_angle
-- ----------------------------
DROP TABLE IF EXISTS `msg_angle`;
CREATE TABLE `msg_angle`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` float NULL DEFAULT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg_event
-- ----------------------------
DROP TABLE IF EXISTS `msg_event`;
CREATE TABLE `msg_event`  (
  `event_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '事件ID',
  `trigger_name` int(0) NULL DEFAULT NULL,
  `deal_no` int(0) NULL DEFAULT NULL,
  `create_time` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '事件发生时间',
  `grade` int(0) NULL DEFAULT NULL,
  `event_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`event_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg_tilt
-- ----------------------------
DROP TABLE IF EXISTS `msg_tilt`;
CREATE TABLE `msg_tilt`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  `value_1x` float(4, 0) NULL DEFAULT NULL,
  `value_1y` float(4, 0) NULL DEFAULT NULL,
  `value_2x` float(4, 0) NULL DEFAULT NULL,
  `value_2y` float(4, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg_trigger
-- ----------------------------
DROP TABLE IF EXISTS `msg_trigger`;
CREATE TABLE `msg_trigger`  (
  `trigger_id` int(0) NOT NULL AUTO_INCREMENT,
  `trigger_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade` int(0) NOT NULL,
  `equip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `data` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `le_value` float NOT NULL,
  `ge_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL,
  `trigger_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`trigger_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg_vibration
-- ----------------------------
DROP TABLE IF EXISTS `msg_vibration`;
CREATE TABLE `msg_vibration`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  `value_v` float NULL DEFAULT NULL,
  `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value_h` float NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg_water_level
-- ----------------------------
DROP TABLE IF EXISTS `msg_water_level`;
CREATE TABLE `msg_water_level`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` float NULL DEFAULT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg_weather_general
-- ----------------------------
DROP TABLE IF EXISTS `msg_weather_general`;
CREATE TABLE `msg_weather_general`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `temperature` float NULL DEFAULT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `humidity` float NULL DEFAULT NULL,
  `air_pressure` float NULL DEFAULT NULL,
  `wind_speeed` float NULL DEFAULT NULL,
  `wind_direction` float NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg_weather_rainfall
-- ----------------------------
DROP TABLE IF EXISTS `msg_weather_rainfall`;
CREATE TABLE `msg_weather_rainfall`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  `value` float NULL DEFAULT NULL,
  `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for msg_weather_visibility
-- ----------------------------
DROP TABLE IF EXISTS `msg_weather_visibility`;
CREATE TABLE `msg_weather_visibility`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` float NULL DEFAULT NULL,
  `time` bigint(0) NULL DEFAULT NULL,
  `topic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Triggers structure for table msg_water_level
-- ----------------------------
DROP TRIGGER IF EXISTS `test`;
delimiter ;;
CREATE TRIGGER `test` AFTER INSERT ON `msg_water_level` FOR EACH ROW BEGIN
	IF
		NEW.value > 11 AND NEW.value < 22 
	THEN
			INSERT INTO msg_event ( trigger_name, create_time, grade, event_desc )
		VALUES
			( 'test' ,URRENT_TIMESTAMP (), 1, '111122' );
	END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
