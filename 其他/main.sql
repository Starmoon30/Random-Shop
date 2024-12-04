/*
 Navicat Premium Dump SQL

 Source Server         : randomshop
 Source Server Type    : SQLite
 Source Server Version : 3045000 (3.45.0)
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3045000 (3.45.0)
 File Encoding         : 65001

 Date: 04/12/2024 23:05:18
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
  "GShelf" integer DEFAULT 0
);

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
-- Table structure for StockHistory
-- ----------------------------
DROP TABLE IF EXISTS "StockHistory";
CREATE TABLE "StockHistory" (
  "SHID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "SHTime" DATE,
  "SHReason" integer,
  "GID" INTEGER NOT NULL,
  "SHStock_O" INTEGER,
  "SHStock_N" INTEGER,
  FOREIGN KEY ("GID") REFERENCES "Goods" ("GID") ON DELETE NO ACTION ON UPDATE NO ACTION
);

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
-- Table structure for sqlite_sequence
-- ----------------------------
DROP TABLE IF EXISTS "sqlite_sequence";
CREATE TABLE "sqlite_sequence" (
  "name",
  "seq"
);

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
UPDATE "sqlite_sequence" SET seq = 22 WHERE name = 'GoodsPics';

-- ----------------------------
-- Auto increment value for Order
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 11 WHERE name = 'Order';

-- ----------------------------
-- Auto increment value for StockHistory
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 1 WHERE name = 'StockHistory';

PRAGMA foreign_keys = true;
