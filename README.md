# 家庭作业(工程化jvm项目)

[![vertx.io](https://img.shields.io/badge/vert.x-4.2.7-purple.svg?logo=eclipsevertdotx)](https://vertx.io)
[![Kotlin](https://img.shields.io/badge/kotlin-1.6.10-blue.svg?logo=kotlin)](http://kotlinlang.org)

这个项目使用 http://start.vertx.io 生成，并基于面试需求改造。

请先阅读说明、要求，最后按照需求完成项目。

## 说明

- 你可以使用任意语言实现，但是要求使用JVM语言（其他JVM语言可以自己集成，如：Java,Scala,Groovy,Clojure,JRuby,Jython 等）
- JDK版本，但要求大于等于`11`
- 欢迎使用各种"奇技淫巧"来整活，但不要让你的代码出现bug，或者让单元测试不通过(最好也不要让别人看到难以理解的代码)
- 必要的时候，你可以使用任何你熟悉的第三方库或工具
- 并不是要求你完全实现所有功能/需求，只需要你能力所能及的尽量完成所有内容
- 请保持良好的代码习惯，提交习惯

## 要求

- [x] **不需要实现Web接口，在单元测试中体现需求与测试用例即可**
- [x] 要求单元测试覆盖率不低于20%，核心代码必须被覆盖
- [x] 请注意观察现有的单元测试示例，并按照示范完成它
- [x] 完成需求里的内容
- [x] 集成CI(Github CI、Travis CI、CircleCI、Gitlab CI，任选其一)
    - [x] 实现构建
    - [x] 实现测试
    - [x] 版本化管理
- [x] 完成本文档里 TODO 的内容，让我们能快速验证你的实现
- [ ] 尽可能的优化你察觉到的项目问题
- [x] 完成后，请更新你的`README`，以便让我们快速了解当前的完成度

## 需求1 - 面包

提示：

- 所有面包过期时间均以 （当前时间 - 生产时间） 为准。

假设，你现在正在维护一个面包店的售卖结算系统，需要实现以下功能：

1. 售卖几种面包，每种面包的价格不同，并且保质期也不同，具体如下：
    1. 全麦面包：价格为：￥12.00，保质期为：2天
    2. 杂粮面包：价格为：￥10.00，保质期为：3天
    3. 金枪鱼三明治：价格为：￥12.00，保质期为：1天
2. 有肉的面包过期之后，需要立刻销毁，无法出库（异常）。
3. 全麦面包，在过期当天可以半价出售。
4. 杂粮面包，在过期当天的早上07:00-09:00免费领取。

## 需求2 - 待定

TODO

## 快速启动 && 验证

1. 该项目需要使用JDK17或更高版本运行

#### `BreadTests`单元测试说明：

- `checkWholeWheatBreadPrice`检查未过期和当天过期的全麦面包价格
- `checkMixedGrainBreadPrice`检查未过期和当天过期的杂粮包价格
- `checkTunaSandwichPrice`检查未过期和当天过期的金枪鱼三明治价格

#### `BakeryTests`单元测试说明：

> 该类主要测试`Bakery`和`BreadWarehouse`的逻辑，
> 简单模拟顾客下单，付款，锁定库存等一些真实场景的功能。

- `checkStockAfterSuccessfulPurchase`检查购买成功后对应库存面包数量
- `checkStockAfterFailedPurchase`检查购买失败后对应库存面包数量，具体情况是出库过程中面包过期销毁该面包