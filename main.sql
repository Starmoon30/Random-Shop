/*
 Navicat Premium Dump SQL

 Source Server         : random-shop
 Source Server Type    : SQLite
 Source Server Version : 3045000 (3.45.0)
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3045000 (3.45.0)
 File Encoding         : 65001

 Date: 24/10/2024 08:27:51
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for Buyer
-- ----------------------------
DROP TABLE IF EXISTS "Buyer";
CREATE TABLE "Buyer" (
  "BAccount" TEXT NOT NULL,
  "BPassword" TEXT NOT NULL,
  "BPhone" integer,
  "BLocation
" TEXT,
  PRIMARY KEY ("BAccount")
);

-- ----------------------------
-- Table structure for Categories
-- ----------------------------
DROP TABLE IF EXISTS "Categories";
CREATE TABLE "Categories" (
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
  "GPic" TEXT,
  "GShelf" integer DEFAULT 0,
  "GState" real DEFAULT 0
);

-- ----------------------------
-- Table structure for GoodsPics
-- ----------------------------
DROP TABLE IF EXISTS "GoodsPics";
CREATE TABLE "GoodsPics" (
  "PicID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "GID" INTEGER NOT NULL,
  "PicURL" TEXT NOT NULL,
  FOREIGN KEY ("GID") REFERENCES "Goods" ("GID") ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- ----------------------------
-- Table structure for Order
-- ----------------------------
DROP TABLE IF EXISTS "Order";
CREATE TABLE "Order" (
  "OID" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  "GID" INTEGER NOT NULL,
  "BAccount" TEXT NOT NULL,
  "ODesc" TEXT,
  "OState" integer DEFAULT 0
);

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
-- Table structure for sqlite_sequence
-- ----------------------------
DROP TABLE IF EXISTS "sqlite_sequence";
CREATE TABLE "sqlite_sequence" (
  "name",
  "seq"
);

-- ----------------------------
-- Auto increment value for Goods
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 4 WHERE name = 'Goods';

-- ----------------------------
-- Auto increment value for Order
-- ----------------------------
UPDATE "sqlite_sequence" SET seq = 3 WHERE name = 'Order';

PRAGMA foreign_keys = true;
