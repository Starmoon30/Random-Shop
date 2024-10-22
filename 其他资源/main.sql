/*
 Navicat Premium Dump SQL

 Source Server         : random-shop
 Source Server Type    : SQLite
 Source Server Version : 3045000 (3.45.0)
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3045000 (3.45.0)
 File Encoding         : 65001

 Date: 16/10/2024 20:49:04
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for Goods
-- ----------------------------
DROP TABLE IF EXISTS "Goods";
CREATE TABLE "Goods" (
  "GID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "GName" TEXT,
  "GDesc" TEXT,
  "GValue" integer,
  "GPic" TEXT,
  "GShelf" integer DEFAULT 0,
  "GState" real DEFAULT 0
);

-- ----------------------------
-- Records of Goods
-- ----------------------------
INSERT INTO "Goods" VALUES (1, '测试商品1', '这是测试商品1的描述', 100, '1.jpeg', 2, 1.0);

-- ----------------------------
-- Table structure for Order
-- ----------------------------
DROP TABLE IF EXISTS "Order";
CREATE TABLE "Order" (
  "OID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "GID" INTEGER,
  "ODesc" TEXT,
  "OState" integer DEFAULT 0
);

-- ----------------------------
-- Records of Order
-- ----------------------------
INSERT INTO "Order" VALUES (1, 1, '小王想要，联系电话114514', 1);
INSERT INTO "Order" VALUES (2, 1, '123', 0);
INSERT INTO "Order" VALUES (3, 1, '234', 0);

-- ----------------------------
-- Table structure for Seller
-- ----------------------------
DROP TABLE IF EXISTS "Seller";
CREATE TABLE "Seller" (
  "SAccount" TEXT NOT NULL,
  "SPassword" TEXT NOT NULL,
  PRIMARY KEY ("SAccount")
);

-- ----------------------------
-- Records of Seller
-- ----------------------------
INSERT INTO "Seller" VALUES ('123', '123');

-- ----------------------------
-- Table structure for sqlite_sequence
-- ----------------------------
DROP TABLE IF EXISTS "sqlite_sequence";
CREATE TABLE "sqlite_sequence" (
  "name",
  "seq"
);

-- ----------------------------
-- Records of sqlite_sequence
-- ----------------------------
INSERT INTO "sqlite_sequence" VALUES ('Goods', 4);
INSERT INTO "sqlite_sequence" VALUES ('Order', 3);

-- ----------------------------
-- Auto increment value for Goods
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 4 WHERE name = 'Goods';

-- ----------------------------
-- Auto increment value for Order
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 3 WHERE name = 'Order';

PRAGMA foreign_keys = true;
