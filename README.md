# 项目总计划

## 1.1 项目迭代概览

- 迭代划分：本项目共分为2个迭代周期，具体如下：
  - 迭代1: 2024年9月19日-2024年10月9日
  - 迭代2: 2024年10月10日-2024年10月17日

## 1.2 当前项目进度

- 进度百分比：截至本报告提交日，项目总体进度约为53.5%
- 估算依据：基于升级标准的五个维度（需求、代码、构建、部署、运行）来评估项目的总体进度。其中权重划分如下：

| 维度 | 权重 | 原因 | 完成情况 |
| --- | --- | --- | --- |
| 需求 | 20% | 需求是一个项目成功的关键，需求收集、分析和确认需要一定的时间和精力；由于基线需求项目较为简单，需求变更的可能性相对较小，所以占比略低于代码。 | 已完成 |
| 代码 | 45% | 代码是项目实现的核心，其质量直接影响项目的质量和交付时间，所以权重最高。 | 50%还未完成 |
| 构建 | 20% | 构建阶段包括将代码编译和集成，确保所有模块能够正确协同工作。构建是项目交付前的重要环节，但对于需求和代码来说，其重要性略低，所以权重适中。 | 30% |
| 部署 | 5% | 部署阶段将软件发布到生产环境中，确保其能够在用户环境中正常运行。安装部署是项目交付的关键环节，但通常不会涉及太多的技术难题，所以权重偏低。 | 未完成 |
| 运行 | 10% | 运行阶段涉及软件的实际使用和维护。运行测试是确保项目质量和用户满意度的关键环节，但通常会在项目后期进行，其重要性在一定程度上依赖于前期需求和代码的质量，所以权重适中。 | 50%未完成 |

# 项目当前迭代期定义

迭代起止日期：2024年9月19日-2024年10月9日

# 项目当前迭代目标定义

本次迭代的主要目标如下：

## 3.1 需求分析：

- 完成需求规格说明书的编写；

## 3.2 功能需求：

- 完成登录、发布商品、冻结商品、撤下商品、查看意向购买人信息、查看商品信息这6个功能。以及所涉及到的数据库与表的设计；

## 3.3 测试：

- 完成上述6个功能的测试，并撰写相关缺陷报告。

# 当前迭代期的任务与成果物

| 任务名称 | 负责人 | 预计成果物 | 任务完成情况 | 具体成果物 |
| --- | --- | --- | --- | --- |
| 前端 | 管瀚博 | 涉及前端的所有源代码.zip文件 | 已完成 | [源代码](https://github.com/1023097618/hahashop/tree/master/hahashop-page) <br> [前端学习笔记](https://github.com/1023097618/hahashop/tree/master/2211080131-%E9%A2%9D外成果物/%E5%89%8D%E7%AB%AF_%E5%8D%8E%E4%B8%B0%E5%B9%B3%E6%9D%BF%E7%AC%94%E8%AE%B0.pdf) |
| 后端 | 管瀚博 | 涉及后端的所有源代码.zip文件 | 已完成 | [源代码](https://github.com/1023097618/hahashop/tree/master/hahashop-serve) |
| 需求规格说明书撰写 | 邓嘉文 | 需求规格说明书文档 | 已完成 | [需求规格说明书](https://gitee.com/Starmoon30/random-shop/wikis/%E9%9C%80%E6%B1%82%E6%96%87%E6%A1%A3)|
| 数据库设计+数据库查询代码编写 | 翁逸轩| ·有关数据库设计的.db文件 ·有关查询语句的.xml文件 | 已完成 | .db文件 [hahashop/hahashop-serve/test.db at master · 1023097618/hahashop (github.com)](https://github.com/1023097618/hahashop/blob/master/hahashop-serve/test.db) .xml文件 [hahashop/hahashop-serve/src/main/resources/mybatis/mapper at master · 1023097618/hahashop (github.com)](https://github.com/1023097618/hahashop/tree/master/hahashop-serve/src/main/resources/mybatis/mapper)|
| 第一次阶段性评审报告文书 | 翁逸轩| ·第一次阶段性评审报告文档 | 已完成 | |
| 自动化测试 | 汪昊 | ·自动化测试及缺陷复现的视频 ·相关源代码.zip文件 ·缺陷报告 | 已完成 | 相关视频[hahashop/测试/附件 at master · 1023097618/hahashop (github.com)](https://github.com/1023097618/hahashop/tree/master/%E6%B5%8B%E8%AF%95/%E9%99%84%E4%BB%B6) 源代码[hahashop/测试/自动化测试代码 at master · 1023097618/hahashop (github.com)](https://github.com/1023097618/hahashop/tree/master/%E6%B5%8B%E8%AF%95/%E8%87%AA%E5%8A%A8%E5%8C%96%E6%B5%8B%E8%AF%95%E4%BB%A3%E7%A0%81) 缺陷报告[hahashop/测试 at master · 1023097618/hahashop (github.com)](https://github.com/1023097618/hahashop/tree/master/%E6%B5%8B%E8%AF%95)|
| 部署文档编写 | 吴炜铖 | ·部署文档 | 已完成 | |