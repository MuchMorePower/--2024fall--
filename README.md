# 网络应用开发实验--在线购物网站--2024fall

姓名: 戴运

学号: 202230440346

[网址连接:http://8.140.235.154:8080/onlineshopping/login.jsp](http://8.140.235.154:8080/onlineshopping/login.jsp)


**登录口令**

管理员: 

    用户名:admin (本系统只有一个管理员角色,不可自由创建)

    密码:123456

顾客:

    用户名:ydai (可以自己在Register页面自由创建)

    密码:123456


## 在线购物网站--基本功能要求

### 顾客端:

    顾客的 注册，登录，注销

    展示产品列表

    用户购买流程（浏览/查询 -> 添加至购物车 -> 付款 -> 发送电子邮件确认发货）

    可以查看订单状态和历史。


### 消费管理端

    商品目录的 管理（包括最基本的添加，删除，修改等操作）

    订单管理、以及销售统计报表

    客户管理、以及客户的 浏览/购买 的日志记录


### 数据库
    基于MySQL

### 项目结构

```
/YourProject
│
├── /src
│   │
│   ├── /config             
│   │   ├── DatabaseConfig.java│   │
│   ├── /dao                 # 数据访问层 (Data Access Object)
│   │   ├── BaseDao.java
│   │   ├── UserDao.java
│   │   ├── CartDao.java
│   │   ├── CartItemDao.java
│   │   ├── OrderDao.java
│   │   ├── OrderItemDao.java
│   │   ├── SalesReportDao.java
│   │   ├── BrowseHistoryDao.java
│   │   └── ProductDao.java
│   │
│   ├── /model                # 模型层
│   │   ├── User.java
│   │   ├── Cart.java
│   │   ├── CartItem.java
│   │   ├── Order.java
│   │   ├── OrderItem.java
│   │   ├── SalesReport.java
│   │   ├── BrowseHistory.java
│   │   └── Product.java
│   │
│   ├── /servlet              # 控制层（Servlets）
│   │   ├── /admin            # 管理员相关的 servlet
│   │   │   ├── ProductManagementServlet.java
│   │   │   ├── CustomerDetailServlet.java
│   │   │   ├── CustomerListServlet.java
│   │   │   ├── OrderDetailServlet.java
│   │   │   ├── OrderListServlet.java
│   │   │   └── AdminSalesReportServlet.java
│   │   ├── /customer         # 客户端相关的 servlet
│   │   │   ├── RegisterServlet.java
│   │   │   ├── LoginServlet.java
│   │   │   ├── LogoutServlet.java
│   │   │   ├── OrderHistoryServlet
│   │   │   ├── ProcessPaymentServlet.java
│   │   │   ├── CartServlet.java
│   │   │   ├── ProductDetailServlet.java
│   │   │   └── ProductListServlet.java
│   │   
│   │
│   ├── /filter               # 过滤器
│   │   ├── AuthenticationFilter.java
│   │   └── EncodingFilter.java
│   │
│   ├── /util                 # 工具包
│   │   └── EmailUtil.java
│   │
│   └── /config               # 配置文件
│       └── DatabaseConfig.java
│
├── /webapp                   # Web资源文件夹
│   ├── /admin                # 管理员相关 JSP 页面
│   │   ├── CustomerDetail.jsp
│   │   ├── CustomerList.jsp
│   │   ├── home.jsp
│   │   ├── OrderDetail.jsp
│   │   ├── OrderList.jsp
│   │   ├── productManagement.jsp
│   │   └── SalesReport.jsp
│   │
│   ├── /customer             # 客户相关 JSP 页面
│   │   ├── cart.jsp
│   │   ├── checkout.jsp
│   │   ├── home.jsp
│   │   ├── logout.jsp
│   │   ├── orderHistory.jsp
│   │   ├── paymentSuccess.jsp
│   │   ├── productDetail.jsp
│   │   └── productList.jsp
│   │
│   ├── login.jsp             # 登录页面
│   ├── register.jsp          # 注册页面
│
│   ├── /WEB-INF              # WEB-INF 文件夹（配置文件）
│   │   └── web.xml           # 配置文件
```

项目是一个典型的基于 Java 的 Web 应用，采用分层架构设计。项目分为多个包（package），每个包负责不同的功能模块。

# 项目结构

## 1. **/src**：源代码目录
项目的核心源代码都位于 `src` 文件夹中，包含了 `config`、`dao`、`model`、`servlet`、`filter`、`util` 等子模块。

### 1.1 **/config**：配置文件
该目录包含数据库配置文件，主要用于配置项目中数据库连接等相关设置。

- `DatabaseConfig.java`：配置数据库连接，管理数据库连接池等。

### 1.2 **/dao**：数据访问层（DAO）
该目录包含了与数据库交互的所有数据访问对象（DAO）类，主要负责数据的增删改查（CRUD）操作。

| 文件名             | 说明                               |
| ------------------ | ---------------------------------- |
| `BaseDao.java`      | 基础 DAO 类，提供通用的数据库操作方法。 |
| `UserDao.java`      | 用户数据访问对象，用于操作 `users` 表。 |
| `CartDao.java`      | 购物车数据访问对象，用于操作 `carts` 表。 |
| `CartItemDao.java`  | 购物车项数据访问对象，用于操作 `cart_items` 表。 |
| `OrderDao.java`     | 订单数据访问对象，用于操作 `orders` 表。 |
| `OrderItemDao.java` | 订单项数据访问对象，用于操作 `order_items` 表。 |
| `SalesReportDao.java`| 销售报告数据访问对象，用于操作销售数据。 |
| `BrowseHistoryDao.java`| 浏览历史数据访问对象，用于操作 `browse_history` 表。 |
| `ProductDao.java`   | 产品数据访问对象，用于操作 `products` 表。 |

### 1.3 **/model**：模型层
该目录包含了与数据库表对应的模型类（实体类），每个模型类对应数据库中的一张表。

| 文件名              | 说明                             |
| ------------------- | -------------------------------- |
| `User.java`          | 用户模型类，映射 `users` 表。     |
| `Cart.java`          | 购物车模型类，映射 `carts` 表。   |
| `CartItem.java`      | 购物车项模型类，映射 `cart_items` 表。 |
| `Order.java`         | 订单模型类，映射 `orders` 表。   |
| `OrderItem.java`     | 订单项模型类，映射 `order_items` 表。 |
| `SalesReport.java`   | 销售报告模型类，映射销售数据。   |
| `BrowseHistory.java` | 浏览历史模型类，映射 `browse_history` 表。 |
| `Product.java`       | 产品模型类，映射 `products` 表。 |

### 1.4 **/servlet**：控制层（Servlets）
该目录包含了所有的 Servlet 类，Servlet 负责处理 HTTP 请求并返回响应，是 MVC 架构中的控制器部分。根据业务不同，分为管理员相关和客户相关的 Servlet。

#### **/admin**：管理员相关的 Servlet
- `ProductManagementServlet.java`：处理产品管理功能。
- `CustomerDetailServlet.java`：查看客户详细信息。
- `CustomerListServlet.java`：展示客户列表。
- `OrderDetailServlet.java`：查看订单详情。
- `OrderListServlet.java`：展示订单列表。
- `AdminSalesReportServlet.java`：生成管理员销售报告。

#### **/customer**：客户相关的 Servlet
- `RegisterServlet.java`：处理用户注册功能。
- `LoginServlet.java`：处理用户登录功能。
- `LogoutServlet.java`：处理用户退出登录功能。
- `OrderHistoryServlet.java`：展示客户订单历史。
- `ProcessPaymentServlet.java`：处理支付功能。
- `CartServlet.java`：管理客户购物车。
- `ProductDetailServlet.java`：查看产品详情。
- `ProductListServlet.java`：展示产品列表。

### 1.5 **/filter**：过滤器
该目录包含了 Web 应用的过滤器类，过滤器在 Servlet 之前或之后进行特定处理。

| 文件名                     | 说明                             |
| -------------------------- | -------------------------------- |
| `AuthenticationFilter.java` | 用于验证用户的身份，确保访问某些资源时用户已登录。 |
| `EncodingFilter.java`       | 设置请求和响应的字符编码。       |

### 1.6 **/util**：工具包
该目录包含一些常用的工具类，提供项目中通用的功能。

| 文件名         | 说明                             |
| -------------- | -------------------------------- |
| `EmailUtil.java` | 用于发送电子邮件的工具类。        |

---

## 2. **/webapp**：Web 资源文件夹
该目录包含 Web 应用的前端资源文件，包括 JSP 页面和配置文件。

### 2.1 **/admin**：管理员相关 JSP 页面
该目录包含管理员相关的页面，管理员可以通过这些页面进行系统管理。

| 文件名                    | 说明                             |
| ------------------------- | -------------------------------- |
| `CustomerDetail.jsp`       | 查看客户详细信息的页面。         |
| `CustomerList.jsp`         | 展示客户列表的页面。             |
| `home.jsp`                 | 管理员首页。                     |
| `OrderDetail.jsp`          | 查看订单详情的页面。             |
| `OrderList.jsp`            | 展示订单列表的页面。             |
| `productManagement.jsp`    | 产品管理页面。                   |
| `SalesReport.jsp`          | 销售报告页面。                   |

### 2.2 **/customer**：客户相关 JSP 页面
该目录包含客户相关的页面，客户可以通过这些页面进行操作。

| 文件名                  | 说明                             |
| ----------------------- | -------------------------------- |
| `cart.jsp`              | 展示购物车的页面。               |
| `checkout.jsp`          | 结账页面。                       |
| `home.jsp`              | 客户首页。                       |
| `logout.jsp`            | 登出页面。                       |
| `orderHistory.jsp`      | 客户订单历史页面。               |
| `paymentSuccess.jsp`    | 支付成功页面。                   |
| `productDetail.jsp`     | 查看产品详情的页面。             |
| `productList.jsp`       | 展示产品列表的页面。             |

### 2.3 **login.jsp**：登录页面
展示用户登录的界面。

### 2.4 **register.jsp**：注册页面
展示用户注册的界面。

### 2.5 **/WEB-INF**：WEB 配置文件夹
该目录包含 Web 应用的配置文件。

| 文件名      | 说明                           |
| ----------- | ------------------------------ |
| `web.xml`   | Web 应用的配置文件，定义了 Servlet、过滤器、监听器等。 |

---

## 总结
该项目采用了经典的 MVC 架构，分为模型层（Model）、视图层（View）、控制层（Controller）。具体地：

- **模型层（Model）**：包含实体类，表示数据库中的表。
- **视图层（View）**：包含 JSP 页面，负责展示界面给用户。
- **控制层（Controller）**：包含 Servlet，负责业务逻辑处理和请求响应。


# 数据库表结构

**shopping.sql**为创建数据库基本表的代码，完成了初始的设置

## `users` 表
存储用户信息。

| 字段名      | 数据类型      | 约束                       | 说明                 |
| ----------- | ------------- | -------------------------- | -------------------- |
| `id`        | INT           | PRIMARY KEY AUTO_INCREMENT | 用户 ID（主键）      |
| `username`  | VARCHAR(255)   | NOT NULL, UNIQUE            | 用户名，唯一         |
| `password`  | VARCHAR(255)   | NOT NULL                    | 用户密码             |
| `email`     | VARCHAR(255)   | NOT NULL                    | 用户电子邮件地址     |
| `user_role` | VARCHAR(50)    | NOT NULL                    | 用户角色（如 `admin` 或 `customer`） |
| `created_at`| TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP   | 创建时间（默认当前时间） |

---

## `products` 表
存储产品信息。

| 字段名      | 数据类型      | 约束                       | 说明                 |
| ----------- | ------------- | -------------------------- | -------------------- |
| `id`        | INT           | AUTO_INCREMENT PRIMARY KEY | 产品 ID（主键）      |
| `name`      | VARCHAR(100)   | NOT NULL                    | 产品名称             |
| `description`| TEXT         | NULL                        | 产品描述             |
| `price`     | DECIMAL(10, 2) | NOT NULL                    | 产品价格             |
| `stock`     | INT           | NOT NULL                    | 产品库存             |
| `created_at`| TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP   | 创建时间（默认当前时间） |

---

## `carts` 表
存储购物车信息。

| 字段名      | 数据类型      | 约束                       | 说明                 |
| ----------- | ------------- | -------------------------- | -------------------- |
| `id`        | INT           | AUTO_INCREMENT PRIMARY KEY | 购物车 ID（主键）    |
| `user_id`   | INT           | NOT NULL                    | 用户 ID（外键，参照 `users` 表） |
| `total_price`| DECIMAL(10, 2) | NOT NULL DEFAULT 0.00       | 购物车总价           |
| `created_at`| TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP   | 创建时间（默认当前时间） |

---

## `cart_items` 表
存储购物车项信息，关联购物车和产品。

| 字段名      | 数据类型      | 约束                       | 说明                 |
| ----------- | ------------- | -------------------------- | -------------------- |
| `id`        | INT           | AUTO_INCREMENT PRIMARY KEY | 购物车项 ID（主键）  |
| `cart_id`   | INT           | NOT NULL                    | 购物车 ID（外键，参照 `carts` 表） |
| `product_id`| INT           | NOT NULL                    | 产品 ID（外键，参照 `products` 表） |
| `quantity`  | INT           | NOT NULL                    | 产品数量             |

---

## `orders` 表
存储订单信息。

| 字段名      | 数据类型      | 约束                       | 说明                 |
| ----------- | ------------- | -------------------------- | -------------------- |
| `id`        | INT           | AUTO_INCREMENT PRIMARY KEY | 订单 ID（主键）      |
| `user_id`   | INT           | NOT NULL                    | 用户 ID（外键，参照 `users` 表） |
| `total`     | DECIMAL(10, 2) | NOT NULL                    | 订单总额             |
| `status`    | VARCHAR(50)    | DEFAULT 'Pending'           | 订单状态（如 `Pending` 或 `Completed`） |
| `created_at`| TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP   | 创建时间（默认当前时间） |

---

## `order_items` 表
存储订单项信息，关联订单和产品。

| 字段名      | 数据类型      | 约束                       | 说明                 |
| ----------- | ------------- | -------------------------- | -------------------- |
| `id`        | INT           | AUTO_INCREMENT PRIMARY KEY | 订单项 ID（主键）    |
| `order_id`  | INT           | NOT NULL                    | 订单 ID（外键，参照 `orders` 表） |
| `product_id`| INT           | NOT NULL                    | 产品 ID（外键，参照 `products` 表） |
| `quantity`  | INT           | NOT NULL                    | 产品数量             |

---

## `browse_history` 表
存储用户浏览历史信息。

| 字段名      | 数据类型      | 约束                       | 说明                 |
| ----------- | ------------- | -------------------------- | -------------------- |
| `id`        | INT           | AUTO_INCREMENT PRIMARY KEY | 浏览历史 ID（主键）  |
| `user_id`   | INT           | NOT NULL                    | 用户 ID（外键，参照 `users` 表） |
| `product_id`| INT           | NOT NULL                    | 产品 ID（外键，参照 `products` 表） |
| `browse_time`| TIMESTAMP    | DEFAULT CURRENT_TIMESTAMP   | 浏览时间（默认当前时间） |

---

## 数据库表之间的外键关系：

- `users` 表的 `username` 列具有唯一约束。
- `carts` 表的 `user_id` 列与 `users` 表的 `id` 列通过外键关联。
- `cart_items` 表的 `cart_id` 列与 `carts` 表的 `id` 列，`product_id` 列与 `products` 表的 `id` 列通过外键关联。
- `orders` 表的 `user_id` 列与 `users` 表的 `id` 列通过外键关联。
- `order_items` 表的 `order_id` 列与 `orders` 表的 `id` 列，`product_id` 列与 `products` 表的 `id` 列通过外键关联。
- `browse_history` 表的 `user_id` 列与 `users` 表的 `id` 列，`product_id` 列与 `products` 表的 `id` 列通过外键关联。

---



