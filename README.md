# Simple Projects - Java Web 示例项目集

## 项目简介

Simple Projects 是一个包含多个轻量级 Java Web 示例项目的集合，旨在展示不同场景下的 Spring MVC 应用开发实践。该项目集涵盖了从基础的 Web 应用到复杂的业务逻辑处理，每个子项目都专注于特定的技术点和应用场景。

## 技术栈

### 核心框架
- **Spring MVC** 3.2.0.RELEASE - Web MVC 框架
- **Spring JDBC** - 数据访问层
- **Spring Context** - IoC 容器
- **Spring AOP** - 面向切面编程

### 数据库相关
- **BoneCP** 0.7.1.RELEASE - 数据库连接池
- **SQL Server JDBC Driver** - 支持 SQL Server 数据库
- **MySQL Connector** - 支持 MySQL 数据库

### 前端技术
- **JSP** & **JSTL** 1.2 - 页面模板
- **AJAX** - 异步数据交互
- **JSChart 3.0** - 图表展示
- **jQuery** - JavaScript 库

### 工具库
- **Apache POI** 3.8 - Excel 文件处理
- **Jackson** 1.8.1 - JSON 序列化/反序列化
- **Apache Commons** - 通用工具类
  - commons-lang3 3.1
  - commons-collections 3.2.1
  - commons-fileupload 1.2.2
  - commons-io 2.3
- **CGLIB** 2.2.2 - 动态代理
- **Javassist** 3.16.1-GA - 字节码操作

### 构建工具
- **Maven** - 项目构建和依赖管理
- **Jetty Maven Plugin** / **Tomcat Maven Plugin** - 快速部署测试

## 项目结构

```
simple-projects/
├── admin-example/          # 后台管理系统示例
├── call-example/           # 异步调用示例
├── demo-test/              # 前端 CSS 模板示例
├── interface-example/      # 接口项目示例
├── judge-plat/             # 数据展示平台（含图表）
├── model-library/          # 手机型号信息爬虫项目
└── price-example/          # 价格管理系统示例
```

## 子项目详细说明

### 1. admin-example - 后台管理系统

**功能特点：**
- 基于 Spring MVC + Spring JDBC 的轻量级 Web 应用
- 全部使用 AJAX 异步加载页面数据
- 支持通过 ModelAndView 导出 Excel 数据视图
- 实现了 Excel 批量导入更新功能，可同时更新多张表的记录
- 包含完整的账单管理和编辑功能

**目录结构：**
- `src/` - Java 源代码
- `WebRoot/` - Web 资源文件
- `sql/` - 数据库脚本
- `doc/` - 文档和截图

### 2. call-example - 异步调用示例

**功能特点：**
- 演示 Spring MVC 异步请求处理
- 实现后台任务异步执行
- 使用 DeferredResult 处理长时间运行的任务
- 定时任务调度示例

**目录结构：**
- `src/` - Java 源代码
- `WebRoot/` - Web 资源文件
- `sql/` - 数据库脚本
- `lib/` - 依赖库

### 3. demo-test - 前端示例

**功能特点：**
- 提供前端 Table CSS 模板案例 (table-css-sample)
- 演示表格样式和布局设计

### 4. interface-example - 接口项目示例

**功能特点：**
- Spring MVC RESTful 接口开发示例
- Spring JDBC 数据访问
- BoneCP 数据库连接池配置
- JSON 数据格式处理和过滤
- 完整的 DAO 层实现

**技术亮点：**
- 标准的三层架构（Controller - Service - DAO）
- 接口文档详见 `doc/price_interface_api.doc`

### 5. judge-plat - 数据展示平台

**功能特点：**
- 基于 Spring MVC 的数据可视化平台
- 集成 JSChart 3.0 图表库，实现数据图表展示
- AJAX 异步数据加载
- BoneCP 数据库连接池
- 完整的分页功能

**技术亮点：**
- Maven 多插件配置（Jetty、Tomcat）
- 丰富的图表展示能力
- 完整的 MVC 架构实现

### 6. model-library - 手机型号信息爬虫

**功能特点：**
- 从中关村在线抓取手机品牌和机型信息
- Web 管理后台界面
- 数据持久化到数据库
- 品牌和型号的增删改查功能

**目录结构：**
- `src/` - Java 源代码
- `WebRoot/` - Web 资源文件
- `modellibrary_db.sql` - 数据库脚本
- `doc/` - 文档和截图

### 7. price-example - 价格管理系统

**功能特点：**
- Spring MVC + Spring JDBC 轻量级 Web 应用
- **自定义缓存实现** - 提升数据访问性能
- 通过过滤器（Filter）加载缓存
- 监听器（Listener）加载配置
- 实现复杂的价格更新和查询逻辑
- 价格判定和交易明细管理

**技术亮点：**
- 完整的缓存机制设计
- 复杂业务逻辑处理示例
- 多表关联查询优化

## 使用方法

### 环境要求
- **JDK**: 1.6 或以上
- **Maven**: 3.0 或以上
- **数据库**: MySQL 或 SQL Server
- **应用服务器**: Tomcat 6/7 或 Jetty

### 快速启动（以 judge-plat 为例）

1. **导入数据库**
```bash
# 执行对应项目的 SQL 脚本
mysql -u root -p < judge-plat/src/main/resources/sql/init.sql
```

2. **配置数据库连接**
编辑 `src/main/resources/dataSource.properties`：
```properties
jdbc.url=jdbc:mysql://localhost:3306/your_database
jdbc.username=your_username
jdbc.password=your_password
```

3. **编译打包**
```bash
cd judge-plat
mvn clean package
```

4. **运行项目**

使用 Jetty（推荐用于开发测试）：
```bash
mvn jetty:run
```

使用 Tomcat：
```bash
mvn tomcat6:run
# 或
mvn tomcat7:run
```

5. **访问应用**
```
http://localhost:9091/judge-plat
```

### Maven 项目导入 IDE

**Eclipse/MyEclipse:**
```bash
mvn eclipse:eclipse
```
然后通过 File → Import → Existing Projects into Workspace 导入

**IntelliJ IDEA:**
直接通过 File → Open 选择项目的 pom.xml 文件

## 依赖说明

### 核心依赖

| 依赖 | 版本 | 说明 |
|------|------|------|
| Spring Framework | 3.2.0.RELEASE | IoC 容器、MVC 框架、数据访问 |
| BoneCP | 0.7.1.RELEASE | 高性能数据库连接池 |
| Jackson | 1.8.1 | JSON 处理库 |
| Apache POI | 3.8 | Excel 文件读写 |
| JSTL | 1.2 | JSP 标签库 |
| JUnit | 4.8 | 单元测试框架 |

### 可选依赖

- **Hibernate JPA** - ORM 框架（部分项目注释掉）
- **Spring Data JPA** - 简化数据访问层开发
- **Apache OpenJPA** - 另一个 JPA 实现

## 项目特色

1. **轻量级设计** - 每个子项目都保持精简，专注于特定功能点
2. **技术多样性** - 覆盖 Web 开发的多个方面：AJAX、异步处理、Excel 导入导出、数据爬虫等
3. **实战导向** - 代码来源于真实业务场景，具有实际参考价值
4. **完整示例** - 每个项目都包含完整的源码、数据库脚本和配置文件
5. **易于学习** - 适合作为 Spring MVC 学习的入门和进阶示例

## 学习路径建议

1. **入门级**：从 `demo-test` 开始，了解基础的前端布局
2. **初级**：学习 `judge-plat`，掌握完整的 MVC 开发流程
3. **中级**：研究 `price-example`，理解缓存机制和复杂业务逻辑
4. **高级**：探索 `admin-example`，学习 Excel 导入导出和批量数据处理
5. **扩展**：尝试 `model-library`，了解爬虫和数据采集技术

## 视频教程

项目配套视频讲解：[simple-projects项目集分析](http://www.tudou.com/programs/view/-3ne8oi_cD0/)

## 注意事项

1. **数据库配置**：每个子项目的数据库连接配置可能不同，请根据实际情况修改
2. **端口冲突**：部分项目配置了不同的端口（如 9091），注意避免端口冲突
3. **依赖版本**：项目使用的框架版本较早，建议在学习后升级到最新稳定版本
4. **字符编码**：所有项目统一使用 UTF-8 编码
5. **SQL 脚本**：运行前请先执行各项目 `sql/` 目录下的数据库初始化脚本

## 开发建议

- 建议使用 Maven 管理项目依赖
- 推荐使用 IntelliJ IDEA 或 Eclipse 作为开发 IDE
- 开发时使用 Jetty 插件可以快速启动和调试
- 生产部署建议使用 Tomcat 或其他成熟的应用服务器

## 许可证

本项目为开源学习项目，仅供学习和参考使用。

## 作者

- GitHub: [luowei](https://github.com/luowei)
- 项目地址: [simple-projects](https://github.com/luowei/simple-projects)

---

**最后更新时间**: 2025-10-05
