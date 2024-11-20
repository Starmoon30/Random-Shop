/*
 Navicat Premium Dump SQL

 Source Server         : randomshop
 Source Server Type    : SQLite
 Source Server Version : 3045000 (3.45.0)
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3045000 (3.45.0)
 File Encoding         : 65001

 Date: 20/11/2024 23:43:04
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for Category
-- ----------------------------
DROP TABLE IF EXISTS "Category";
CREATE TABLE "Category" (
  "CID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "CName" TEXT NOT NULL,
  "CParentID" INTEGER DEFAULT 0
);

-- ----------------------------
-- Records of Category
-- ----------------------------
INSERT INTO "Category" VALUES (0, '全部', -1);
INSERT INTO "Category" VALUES (1, '上装', 0);
INSERT INTO "Category" VALUES (2, '下衣', 0);
INSERT INTO "Category" VALUES (3, '配饰', 0);
INSERT INTO "Category" VALUES (4, '帽子', 1);
INSERT INTO "Category" VALUES (5, '衬衫', 1);
INSERT INTO "Category" VALUES (6, '外套', 1);
INSERT INTO "Category" VALUES (7, '裤子', 2);
INSERT INTO "Category" VALUES (8, '鞋子', 2);
INSERT INTO "Category" VALUES (9, '裙子', 2);
INSERT INTO "Category" VALUES (10, '首饰', 3);
INSERT INTO "Category" VALUES (11, '手饰', 3);
INSERT INTO "Category" VALUES (12, '挂饰', 3);

-- ----------------------------
-- Table structure for Goods
-- ----------------------------
DROP TABLE IF EXISTS "Goods";
CREATE TABLE "Goods" (
  "GID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "GName" TEXT,
  "GDesc" TEXT,
  "GStock" integer DEFAULT 0,
  "GValue" integer,
  "CID" INTEGER,
  "GShelf" integer DEFAULT 0,
  "GState" real DEFAULT 0
);

-- ----------------------------
-- Records of Goods
-- ----------------------------
INSERT INTO "Goods" VALUES (1, '测试商品', '测试商品的描述', 12, 989, 4, 1, 0.0);
INSERT INTO "Goods" VALUES (2, '衬衫一号', '好看的衬衫', 1, 222, 5, 1, 0.0);
INSERT INTO "Goods" VALUES (3, '外套一号', '好看的外套', 1, 998, 6, 1, 0.0);
INSERT INTO "Goods" VALUES (4, '裤子一号', '好看的裤子', 5, 654, 7, 1, 0.0);
INSERT INTO "Goods" VALUES (5, '鞋子一号', '好看的鞋子', 1, 222, 8, 1, 0.0);
INSERT INTO "Goods" VALUES (6, '裙子一号', '好看的裙子', 4, 6033, 12, 1, 0.0);
INSERT INTO "Goods" VALUES (7, '项链一号', '好看的项链', 4, 345, 9, 1, 0.0);
INSERT INTO "Goods" VALUES (8, '手串一号', '好看的手串', 8, 7706, 11, 1, 0.0);
INSERT INTO "Goods" VALUES (9, '耳坠一号', '好看的耳坠', 2, 9680, 10, 1, 0.0);

-- ----------------------------
-- Table structure for GoodsPics
-- ----------------------------
DROP TABLE IF EXISTS "GoodsPics";
CREATE TABLE "GoodsPics" (
  "PicID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "GID" INTEGER NOT NULL,
  "PicData" blob NOT NULL,
  FOREIGN KEY ("GID") REFERENCES "Goods" ("GID") ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- ----------------------------
-- Records of GoodsPics
-- ----------------------------
INSERT INTO "GoodsPics" VALUES (1, 1, '');
INSERT INTO "GoodsPics" VALUES (4, 2, '');
INSERT INTO "GoodsPics" VALUES (7, 2, '');
INSERT INTO "GoodsPics" VALUES (9, 2, '');
INSERT INTO "GoodsPics" VALUES (10, 2, '');

-- ----------------------------
-- Table structure for Order
-- ----------------------------
DROP TABLE IF EXISTS "Order";
CREATE TABLE "Order" (
  "OID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "GID" INTEGER NOT NULL,
  "UAccount" TEXT NOT NULL,
  "OPhone" TEXT,
  "OAddress" TEXT,
  "ORemark" TEXT,
  "OState" integer DEFAULT 0
);

-- ----------------------------
-- Records of Order
-- ----------------------------
INSERT INTO "Order" VALUES (1, 1, 'A1', '114514', '大鸟转转转酒吧', '一个人来', 2);
INSERT INTO "Order" VALUES (2, 2, 'A1', '10099', '浙工商105', '大一点', 2);
INSERT INTO "Order" VALUES (3, 3, 'A1', '10089', '浙工商103', '小一点', 2);
INSERT INTO "Order" VALUES (4, 4, 'A1', '10089', '浙工商103', '随便了', 0);
INSERT INTO "Order" VALUES (5, 5, 'A1', '10089', '浙工商313', '加急', 0);
INSERT INTO "Order" VALUES (6, 6, 'B5', '10099', '浙工商886', '自己的', 0);
INSERT INTO "Order" VALUES (7, 7, 'B5', '10099', '浙工商886', '自己的', 0);
INSERT INTO "Order" VALUES (8, 8, 'B5', '10089', '浙工商313', '给别人的', 0);
INSERT INTO "Order" VALUES (9, 10, 'Z3', '77911', '浙工商楼顶', '速送', 0);
INSERT INTO "Order" VALUES (10, 10, 'Z3', '77911', '浙工商楼顶', '快点发货', 0);
INSERT INTO "Order" VALUES (11, 11, 'Z3', '77911', '浙工商楼顶', '不然差评', 0);

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS "User";
CREATE TABLE "User" (
  "UAccount" TEXT NOT NULL,
  "UPassword" TEXT NOT NULL,
  "UPhone" text,
  "UAddress" TEXT,
  "UCategory" integer,
  PRIMARY KEY ("UAccount")
);

-- ----------------------------
-- Records of User
-- ----------------------------
INSERT INTO "User" VALUES ('admin123', 'admin123', '12345', '浙江温州', 0);
INSERT INTO "User" VALUES ('admin23', 'admin123', '123', 'abc低', 0);
INSERT INTO "User" VALUES ('A1', 'A1', '10089', '浙工商313', 1);
INSERT INTO "User" VALUES ('B5', 'B5', '10099', '浙工商886', 1);
INSERT INTO "User" VALUES ('Z3', 'Z3', '77911', '浙工商楼顶', 1);
INSERT INTO "User" VALUES ('123', '321', '1234', '浙江工商大学1', 1);

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
INSERT INTO "sqlite_sequence" VALUES ('Goods', 9);
INSERT INTO "sqlite_sequence" VALUES ('Order', 11);
INSERT INTO "sqlite_sequence" VALUES ('GoodsPics', 10);
INSERT INTO "sqlite_sequence" VALUES ('Category', 12);

-- ----------------------------
-- Auto increment value for Category
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 12 WHERE name = 'Category';

-- ----------------------------
-- Auto increment value for Goods
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 9 WHERE name = 'Goods';

-- ----------------------------
-- Auto increment value for GoodsPics
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 10 WHERE name = 'GoodsPics';

-- ----------------------------
-- Auto increment value for Order
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 11 WHERE name = 'Order';

PRAGMA foreign_keys = true;
