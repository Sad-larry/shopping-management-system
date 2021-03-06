## 商品管理系统

#### 开发环境

+ 开发集成环境：IDEA
+ 开发工具：Maven 3.8.4
+ JDK版本：OpenJDK 17.0.2
+ 数据库驱动：mysql-connector-java 8.0.29
+ 测试环境：junit 4.13.2

### 数据表

+ sms数据库
+ GOODS表，商品表
+ GSALES表，购物车表
+ SALESMAN表，营业员表


### 管理系统模块

+ 主界面（开发中）
  + 类名：MainPage.java，开始时间2022-6-2
  + 方法名：MainPage.java中的mainPage()，开始时间2022-6-2，完成时间2022-6-2
+ 商品维护界面（已完成）
  + 方法名：MainPage.java中的goodsMain()，开始时间2022-6-2，完成时间2022-6-2
+ 添加商品（已完成）
  + 方法名：MainPage.java中的insertGoods()，开始时间2022-6-2，完成时间2022-6-2
+ 更改商品（已完成）
  + 方法名：MainPage.java中的updateGoods()，开始时间2022-6-2，完成时间2022-6-2
+ 删除商品（已完成）
  + 方法名：MainPage.java中的deleteGoods()，开始时间2022-6-2，完成时间2022-6-2
+ 显示所有商品（已完成）
  + 方法名：MainPage.java中的listGoods()，开始时间2022-6-2，完成时间2022-6-2
+ 查询商品（已完成）
  + 方法名：MainPage.java中的queryGoods()，开始时间2022-6-2，完成时间2022-6-2
+ 前台收银登陆（已完成）
  + 方法名：MainPage.java中的loginPage()，开始时间2022-6-2，完成时间2022-6-2
+ 购物结算（已完成）
  + 方法名：MainPage.java中的saleShopping()，开始时间2022-6-4，完成时间2022-6-4
+ 商品管理界面（已完成）
  + 方法名：MainPage.java中的goodsManager()，开始时间2022-6-2，完成时间2022-6-2
+ 查询当日卖出商品列表（已完成）
  + 方法名：MainPage.java中的listGoodsForSaledToday()，开始时间2022-6-4，完成时间2022-6-4
+ 售货员管理界面（已完成）
  + 方法名：MainPage.java中的salesmanManager()，开始时间2022-6-2，完成时间2022-6-2
+ 添加售货员（已完成）
  + 方法名：MainPage.java中的insertSalesman()，开始时间2022-6-2，完成时间2022-6-2
+ 更改售货员（已完成）
  + 方法名：MainPage.java中的updateSalesman()，开始时间2022-6-2，完成时间2022-6-2
+ 删除售货员（已完成）
  + 方法名：MainPage.java中的deleteSalesman()，开始时间2022-6-2，完成时间2022-6-2
+ 显示所有售货员（已完成）
  + 方法名：MainPage.java中的listSalesman()，开始时间2022-6-2，完成时间2022-6-2
+ 查询售货员（已完成）
  + 方法名：MainPage.java中的querySalesman()，开始时间2022-6-2，完成时间2022-6-2


### 说明

#### utils包

1、InputUtils.java类是用来过滤输入的，里面的都是静态方法，可以直接调用
2、DBUtils.java类是数据库的基本操作，比如获取数据库连接，关闭资源

#### dao包(DAO(Data Access Object,数据访问对象的缩写))

1、GoodsDAO.java类里面实现了对GOODS表的操作
2、GsalesDAO.java类里面实现了对GSALES表的操作
3、SalesmanDAO.java类里面实现了对SALESMAN表的操作，如1.插入用户，2.查询用户

#### pojo包(POJO(Plain Ordinary Java Object)简单的Java对象)

这个就不解释了，里面的包都是映射数据库表，每个类的属性都一一对应着数据表的字段