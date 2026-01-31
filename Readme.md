【1】本文笔记记载

[day37 SpringBoot](https://www.yuque.com/love71/nd1fol/mf5we9#tFAnj)

【2】注意

 **同一个项目里，尽量不要同时使用 JPA + MyBatis + MyBatis-Plus**

## 🚫 为什么不建议三者同时用？

### 1️⃣ MyBatis 和 MyBatis-Plus 本身就是“父子关系”

- **MyBatis-Plus = MyBatis + 一堆增强功能**
- MP 内部已经帮你封装好了：
  - CRUD
  - SQL 注入器
  - 分页插件
  - 自动映射

👉 所以：

> ❌ 同时引 MyBatis + MyBatis-Plus
>  = **功能重复 + 自动配置冲突风险**

你只要 MP 就够了，MyBatis 会被 MP 间接引入。

------

### 2️⃣ JPA 和 MyBatis / MP 是两套完全不同的 ORM 思路

| 对比点     | JPA (Hibernate)                  | MyBatis / MP      |
| ---------- | -------------------------------- | ----------------- |
| 思想       | 面向对象 ORM                     | 面向 SQL          |
| SQL 控制权 | 自动生成 SQL                     | 你自己写 / 半自动 |
| 学习成本   | 概念多（实体状态、缓存、懒加载） | 直观              |
| 排错体验   | 报错抽象，栈深                   | 报错直接指向 SQL  |

👉 三者混在一个项目中：

- 自动配置容易打架
- 实体类注解风格冲突（你已经踩到了）
- 事务管理器容易混
- Debug 难度 ×3

Mybatis和JPA能不能同时使用？

## ✅ 能不能同时使用？——能 ✅

从技术角度：

- JPA（Hibernate）和 MyBatis 是**两套独立体系**
- Spring Boot 也支持：
  - `spring-boot-starter-data-jpa`
  - `mybatis-spring-boot-starter`

只要你配置不冲突（数据源、事务管理器分清楚），**同一个项目同时跑起来是完全可行的**。

👉 很多老项目、迁移项目就是这么干的。

------

## ❌ 该不该同时使用？——一般不该 ❌

### 1️⃣ 维护成本暴增

一个项目里出现两种 DAO 风格：

```
// JPA
UserRepository extends JpaRepository<User, Long>

// MyBatis
UserMapper.selectById(id)
```

后果：

- 新人进来要学两套体系
- 写业务时经常纠结：
   👉 “这个功能用 JPA 还是 MyBatis？”
- 代码风格不统一，后期维护很痛苦

------

### 2️⃣ 实体类会变得很「丑」

你现在的实体：

```
@Table(name = "account")      // JPA
@Id                           // JPA
@GeneratedValue(...)          // JPA
```

如果再加上 MP 或 MyBatis：

```
@TableName("account")         // MP
@TableId(type = IdType.AUTO) // MP
```

👉 一个类上堆两套注解，**可读性直线下降**。

------

### 3️⃣ 事务 & 缓存容易踩坑

- JPA 有一级缓存、二级缓存、持久化上下文
- MyBatis 是“执行即查库”，默认无缓存

如果你：

- 用 JPA 改数据
- 再用 MyBatis 查同一张表

👉 可能出现：

> “我明明更新了，为什么查出来还是旧数据？”

这种坑，新手非常容易踩